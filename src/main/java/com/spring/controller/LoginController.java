package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.entity.Usuario;
import com.spring.model.dao.EmpresaDAO;
import com.spring.model.dao.RolDao;
import com.spring.model.service.UserService;



@Controller
public class LoginController {

	@Autowired 
	UserService usuarioService;
	
	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Autowired
	private RolDao rolDAO;

	@GetMapping("/auth/login")
	public String Login(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		
		return "login";
	}
	
	@GetMapping("/auth/registro")
	public String registroform(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("listadoEmpresa", empresaDAO.findAll());
		model.addAttribute("listadoRol", rolDAO.findAll());
		
		return "registro";
		
	}
	
	@PostMapping("/auth/registro")
	public String registro(@ModelAttribute Usuario usuario, BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			return "redirect:/auth/registro";
		}else {
			model.addAttribute("usuario", usuarioService.registrar(usuario));
		}
		
		return "redirect:/auth/login";
	}
	
	@GetMapping("/403")
	public String error403() {
		return "error/403";
	}
	
}
