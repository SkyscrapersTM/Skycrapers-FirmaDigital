package com.spring.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.entity.PlanBolsaFirma;

public interface PlanBolsaFirmaService {

	public List<PlanBolsaFirma> listarPlanes();
	public Page<PlanBolsaFirma> listAllPlans(Pageable pageable);
	public Optional<PlanBolsaFirma> listarPorId(Integer id);
	public int save(PlanBolsaFirma obj);
	public PlanBolsaFirma buscarPorId(Integer id);
	public void eliminar(Integer id);


}
	

