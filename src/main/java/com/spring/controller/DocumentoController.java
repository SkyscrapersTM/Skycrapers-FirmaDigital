package com.spring.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.entity.Documento;
import com.spring.entity.DocumentoReporte;
import com.spring.model.dao.DocumentoDAO;

@RequestMapping("/skyscrapers")
@Controller
public class DocumentoController {

	@Autowired
	DocumentoDAO documentoDAO;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/documentos/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Documento> listDocuments = documentoDAO.findAll();
         
        DocumentoReporte excelExporter = new DocumentoReporte(listDocuments);
         
        excelExporter.export(response);    
    }
	
	@GetMapping("/documentos/buscarPor")
	public String buscarDocumentosPorId(@Param("iddocumento") Integer iddocumento, Model model) {
		Documento documento = documentoDAO.findOneByIddocumento(1);
		model.addAttribute("documento", documento);
		model.addAttribute("ListDocumentos", documentoDAO.findByEstado("pendiente"));
		return "firmarDocumento";
	}
	
	@PostMapping("/documentos/firmar")
	public String FirmarDocumento(@ModelAttribute Documento documento, RedirectAttributes ra) {
		
		documento.setEstado("finalizado");
		
		documentoDAO.save(documento);
		
		ra.addFlashAttribute("titleFirma", "Exitoso!");
		ra.addFlashAttribute("messageFirma","El documento ha sido firmado correctamente");
		
		return "redirect:/skyscrapers/documentos/listarDocumentos";
	}
	
	@GetMapping("/documentos/listarDocumentos")
	public String listarDocumentos(Model model){
		
		model.addAttribute("ListDocumentos", documentoDAO.findByEstado("pendiente"));
		
		return "listarDocumentos";
	}
	
	@PostMapping("/documentos/listarDocumentos")
	public String submitContact(HttpServletRequest request,
				  @RequestParam("attachment") MultipartFile multipartFile, RedirectAttributes ra
				  ) throws MessagingException, UnsupportedEncodingException {
		
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		
		String mailSubject = fullname + " ha enviado un mensaje";
		String mailContect = "<p><b>De:</b> " + fullname + "</p>";
		mailContect += "<p><b>Asunto:</b> "+ subject + "</p>";
		mailContect += "<p><b>Contenido:</b> "+ content + "</p>";
		
		helper.setFrom("proyectociber123@gmail.com", "Skyscrapers");
		helper.setTo(email);
		helper.setSubject(mailSubject);
		helper.setText(mailContect, true);
			
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			
			InputStreamSource source = new InputStreamSource() {
				
				@Override
				public InputStream getInputStream() throws IOException {
					return multipartFile.getInputStream();
				}
			};
			
			helper.addAttachment(fileName, source);
		}
		
		mailSender.send(message);
		
		ra.addFlashAttribute("title", "Exitoso!");
		ra.addFlashAttribute("message","El correo ha sido enviado correctamente");
		
		return "redirect:/skyscrapers/documentos/listarDocumentos";
	}
	
	@GetMapping("/documentos/listarDocumentos/download")
	public void downloadFile(@Param("iddocumento") Integer iddocumento, HttpServletResponse response) throws Exception {
		
		Documento document = documentoDAO.findOneByIddocumento(iddocumento);

		response.setContentType("application/octect-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + document.getNombre_original();
		response.setHeader(headerKey, headerValue);
		
		ServletOutputStream outputStream = response.getOutputStream();
		
		outputStream.write(document.getLink_original());
		outputStream.close();
	}
	
	@GetMapping("/documentos/consultarDocumentos")
	public String ConsultarDocumento(Model model) {
		
		
		model.addAttribute("ListDocumentos", documentoDAO.findAll());
		
		return "ConsultarDocumentos";
	}
	
	@PostMapping("/documentos/consultarDocumentos")
	public String submitContactConsulta(HttpServletRequest request,
				  @RequestParam("attachment") MultipartFile multipartFile, RedirectAttributes ra
				  ) throws MessagingException, UnsupportedEncodingException {
		
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		
		String mailSubject = fullname + " ha enviado un mensaje";
		String mailContect = "<p><b>De:</b> " + fullname + "</p>";
		mailContect += "<p><b>Asunto:</b> "+ subject + "</p>";
		mailContect += "<p><b>Contenido:</b> "+ content + "</p>";
		
		helper.setFrom("proyectociber123@gmail.com", "Skyscrapers");
		helper.setTo(email);
		helper.setSubject(mailSubject);
		helper.setText(mailContect, true);
			
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			
			InputStreamSource source = new InputStreamSource() {
				
				@Override
				public InputStream getInputStream() throws IOException {
					return multipartFile.getInputStream();
				}
			};
			
			helper.addAttachment(fileName, source);
		}
		
		mailSender.send(message);
		
		ra.addFlashAttribute("title", "Exitoso!");
		ra.addFlashAttribute("message","El correo ha sido enviado correctamente");
		
		return "redirect:/skyscrapers/documentos/consultarDocumentos";
	}
	
	
}
