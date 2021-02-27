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

import com.spring.entity.PlanBolsaFirma;
import com.spring.model.service.PlanBolsaFirmaService;
import com.spring.utils.RenderizadorPaginas;

@Controller
@RequestMapping("/skyscrapers")
public class PlanBolsaFirmaController {

		
	@Autowired
	private PlanBolsaFirmaService planService;
	
	@GetMapping("/listarPlanes")
	public String listarPlanes(@RequestParam(name="page", defaultValue = "0")int page, Model model) {
		
		Pageable planPageable = PageRequest.of(page, 5);
		Page<PlanBolsaFirma> planes = planService.listAllPlans(planPageable);
		RenderizadorPaginas<PlanBolsaFirma> renderizadorPaginas = new RenderizadorPaginas<PlanBolsaFirma>("/skyscrapers/listarPlanes", planes);
		
		//List<PlanBolsaFirma> planes = planService.listarPlanes();
		model.addAttribute("page", renderizadorPaginas);
		model.addAttribute("planes", planes);
		return "listarPlanes";
	}

	@GetMapping("/nuevoPlan")
	public String nuevoPlan(Model model) {
		PlanBolsaFirma bolsafirma = new PlanBolsaFirma();
		model.addAttribute("bolsafirma", bolsafirma);
		return "frmCrearPlan";
	}
	
	@PostMapping("/savePlan")
	public String savePlan(@ModelAttribute PlanBolsaFirma bolsafirma) {
		planService.save(bolsafirma);
		return "redirect:/skyscrapers/listarPlanes";
	}
	
	@GetMapping("/buscarPorIdPlan/{id}")
	public String buscarPorIdPlan(@PathVariable("id")Integer idbolsafirma, Model model){
	PlanBolsaFirma bolsafirma = planService.buscarPorId(idbolsafirma);
	model.addAttribute("titulo", "Formulario: Editar Plan");
	model.addAttribute("bolsafirma", bolsafirma);
		return "frmCrearPlan";
		}
	
	@GetMapping("/eliminarPlan/{id}")
	public String eliminarPlan(@PathVariable("id")Integer idbolsafirma) {
		planService.eliminar(idbolsafirma);
		return "redirect:/skyscrapers/listarPlanes";
	}
	
}
