package com.spring.model.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.entity.Trabajador;
import com.spring.model.dao.TrabajadorDAOPag;
import com.spring.model.dao.TrabajadorRepository;
import com.spring.model.service.TrabajadorService;


@Service
public class TrabajadorServiceImpl implements TrabajadorService{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private TrabajadorRepository trabajadorrepository;
	
	@Autowired
	private TrabajadorDAOPag trabajadorDaoPag;
	

	@Override
	public List<Trabajador> listarTrabajadores() {
		return (List<Trabajador>)trabajadorrepository.findAll();
	}

	@Override
	public Page<Trabajador> listAllTrabajadores(Pageable pageable) {
		return trabajadorDaoPag.findAll(pageable);
	}
	
	@Override
	public Optional<Trabajador> listarId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Trabajador t) {
		int res=0;
		t.setPassword(passwordEncoder.encode(t.getPassword()));
		Trabajador tra=trabajadorrepository.save(t);
		if(!tra.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public Trabajador buscarPorId(Integer id) {
		return trabajadorrepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
		trabajadorrepository.deleteById(id);
		
	}

	

}
