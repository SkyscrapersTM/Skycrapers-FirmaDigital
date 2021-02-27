package com.spring.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.String;
import java.text.SimpleDateFormat;

import com.spring.entity.Documento;
import com.spring.entity.Empresa;
import com.spring.entity.Role;
import com.spring.entity.Usuario;
import com.spring.model.dao.DocumentoRepository;
import com.spring.model.dao.RolDao;
import com.spring.model.dao.UserDAO;
import com.spring.model.impl.DocumentoServicelmpl;
import com.spring.model.service.DocumentoService;
import com.spring.model.service.UploadFileService;
import com.spring.model.service.UserService;
import com.spring.utils.RenderizadorPaginas;


@Controller
@RequestMapping
public class ControladorDocumento {
@Autowired
private DocumentoService service;
@Autowired
private UserService userservice;
@Autowired
private UserDAO userDAO;

@Autowired
private UploadFileService uploadFileService;
@GetMapping("/skyscrapers/subirDocumento")
public String listar(@RequestParam(name="page", defaultValue = "0")int page, Model model) {
	Pageable documentoPageable = PageRequest.of(page, 5);
	Page<Documento> documentos = service.listAllDocumentos(documentoPageable);
	RenderizadorPaginas<Documento> renderizadorPaginas = new RenderizadorPaginas<Documento>("/skyscrapers/subirDocumento", documentos);
	
	//List<Documento> documentos=service.listar();
	model.addAttribute("page", renderizadorPaginas);
	model.addAttribute("documentos", documentos);
	
	
	return "SubirDocumento";
}

@GetMapping("/new")
	public String agregar(Model model) {
		model.addAttribute("documento", new Documento());
		model.addAttribute("listadoRol", userDAO.findByRoles_name("CLI_FIRMANTE"));
		
	return "Agregar";
	}

String pattern = "yyyy-MM-dd";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


@PostMapping("/upload")
public String uploadFile(@RequestParam("link_original") MultipartFile file,
RedirectAttributes ra, 	Model model, Usuario usuario) throws IOException{
    String filename=StringUtils.cleanPath(file.getOriginalFilename());

    Documento documento= new Documento();
    documento.setNombre_original(filename);
    documento.setLink_original(file.getBytes());
    documento.setEstado("Pendiente");
    documento.setFecha_registro(new Date());
    service.save(documento);
   return "redirect:/skyscrapers/subirDocumento/";
    }


/*@PostMapping("/save")

public String save(@Validated Documento d, Model model) {
	service.save(d);
  

	return "redirect:/SubirDocumento";
}
*/

//List<Usuario> userRole =  userDAO.findByRoles_name("CLI_FIRMANTE");
//List<Usuario> users =  userDAO.findByRoles_name("CLI_FIRMANTE");
//model.addAttribute("users",users);
//documento.setUsers(userRole);
	//model.addAttribute("listadoRol",userRole);
// model.addAttribute("usuario", userservice.findByRoles_name(usuario));
 //List<Role> listadoRol = repo.findAll();
	//model.addAttribute("listadoRol",repo.findAll());
/*@GetMapping("/listaUsu")
public ResponseEntity<List<Usuario>> listaUsu(){
	List<Usuario> lista= data.listaUsu();
	return new ResponseEntity(lista,HttpStatus.OK);
}*/
/*
@GetMapping("/lista")
public String listaUsu(Model model) {
	model.addAttribute("listadoRol", repo.findAll());
	return "upload";
}*/
/*
@RequestMapping("/cargaUsuario")
@ResponseBody
public List<Usuario> listaUsu(){
	return data.listaUsu();
	
}*/
/*
@RequestMapping("/cargaRol")
@ResponseBody
public List<Role> listadoRol(){
	return rolservice.listadoRol();
}*/
} 

	