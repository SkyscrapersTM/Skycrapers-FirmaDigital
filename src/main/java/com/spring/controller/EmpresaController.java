package com.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.entity.Empresa;
import com.spring.model.service.EmpresaService;
import com.spring.utils.RenderizadorPaginas;

@Controller
@RequestMapping("/skyscrapers")
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/listar")
	public String listar(@RequestParam(name="page", defaultValue = "0")int page, Model model) {
		
		Pageable empresaPageable = PageRequest.of(page, 5);
		Page<Empresa> empresas = empresaService.listAllEmpresas(empresaPageable);
		RenderizadorPaginas<Empresa> renderizadorPaginas = new RenderizadorPaginas<Empresa>("/skyscrapers/listar", empresas);
		
		//List<Empresa> empresas = empresaService.listar();
		model.addAttribute("page", renderizadorPaginas);
		model.addAttribute("empresas", empresas);
		return "listar";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Empresa empresa = new Empresa();
		model.addAttribute("empresa", empresa);
		return "frmCrear";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute Empresa empresa) {
		empresaService.save(empresa);
		return "redirect:/skyscrapers/listar";
	}
	
	@GetMapping("/buscarPorId/{id}")
	public String buscarPorId(@PathVariable("id")Integer idempresa, Model model){
	Empresa empresa = empresaService.buscarPorId(idempresa);
	model.addAttribute("titulo", "Formulario: Editar Empresa");
	model.addAttribute("empresa", empresa);
		return "frmCrear";
		}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id")Integer idempresa) {
		empresaService.eliminar(idempresa);
		return "redirect:/skyscrapers/listar";
	}
	
}
