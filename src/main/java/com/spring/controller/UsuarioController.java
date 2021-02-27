package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.entity.PlanBolsaFirma;
import com.spring.model.service.PlanBolsaFirmaService;

@RequestMapping("/skyscrapers")
@Controller
public class UsuarioController {

	@Autowired
	private PlanBolsaFirmaService planService;
	
	@GetMapping("/home")
	public String Home(Model model) {
		try {
			
			List<PlanBolsaFirma> planes = planService.listarPlanes();
			model.addAttribute("databolsas",planes);	
						
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "Index";
	}
	
	@GetMapping("/consultarDocumentos")
	public String ConsultarDocumento() {
		
		return "ConsultarDocumentos";
	}
	
	/**@GetMapping("/firmarDocumentos")
	public String FirmarDocumento() {
		
		return "FirmarDocumentos";
	}**/
		
}
