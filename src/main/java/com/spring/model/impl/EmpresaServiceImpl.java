package com.spring.model.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.entity.Empresa;
import com.spring.model.dao.EmpresaDAO;
import com.spring.model.dao.EmpresaDAOPag;
import com.spring.model.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService{
	
	@Autowired
	private EmpresaDAOPag empresaDAODaoPag;

	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Override
	public List<Empresa> listar() {
		return (List<Empresa>)empresaDAO.findAll();
	}

	@Override
	public Page<Empresa> listAllEmpresas(Pageable pageable) {
		return empresaDAODaoPag.findAll(pageable);
	}
	
	@Override
	public int save(Empresa e) {
		int res=0;
		Empresa per=empresaDAO.save(e);
		if(!per.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public Optional<Empresa> listarId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empresa buscarPorId(Integer id) {
		return empresaDAO.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
		empresaDAO.deleteById(id);
	}


}
