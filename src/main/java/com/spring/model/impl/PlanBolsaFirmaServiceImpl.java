package com.spring.model.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.entity.PlanBolsaFirma;
import com.spring.model.dao.PlanBolsaFirmaDAOPag;
import com.spring.model.dao.PlanBolsaFirmaRepository;
import com.spring.model.service.PlanBolsaFirmaService;

@Service
public class PlanBolsaFirmaServiceImpl implements PlanBolsaFirmaService{
	
	@Autowired
private PlanBolsaFirmaRepository planrepository;
	
	@Autowired
private PlanBolsaFirmaDAOPag planDaoPag;


	@Override
	public List<PlanBolsaFirma> listarPlanes() {
		return (List<PlanBolsaFirma>)planrepository.findAll();
	}

	@Override
	public Page<PlanBolsaFirma> listAllPlans(Pageable pageable) {
		return planDaoPag.findAll(pageable);
	}
	

	
	@Override
	public int save(PlanBolsaFirma obj) {
		int res=0;
		PlanBolsaFirma plan=planrepository.save(obj);
		if(!plan.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public Optional<PlanBolsaFirma> listarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanBolsaFirma buscarPorId(Integer id) {
		return planrepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
		planrepository.deleteById(id);
	}

	


}
