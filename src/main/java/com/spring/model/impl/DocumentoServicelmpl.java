package com.spring.model.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.entity.Documento;
import com.spring.entity.Role;
import com.spring.model.dao.DocumentoRepository;
import com.spring.model.dao.DocumentoRepositorypag;
import com.spring.model.service.DocumentoService;
@Service
public class DocumentoServicelmpl implements DocumentoService {

	@Autowired
	private DocumentoRepository data;
	@Autowired
	private DocumentoRepositorypag pag;
	@Override
	public List<Documento> listar() {
		 	
		return (List<Documento>)data.findAll();
	}
	@Override
	public int save(Documento d) {
		int res=0;
		Documento documento= data.save(d);
		if(!documento.equals(null)) {
		res=1;
	}
		return res;
	}
	/*@Override
	public List<Role> listaRol() {
		return data.listaRol();
	}*/
	
	
	@Override
	public Page<Documento> listAllDocumentos(Pageable pageable) {
		return pag.findAll(pageable);

	}


	

	
}