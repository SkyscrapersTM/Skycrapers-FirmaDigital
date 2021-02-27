package com.spring.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.entity.Documento;

public interface DocumentoDAO extends JpaRepository<Documento, Integer> {
	List<Documento>findByEstado(String estado);
	
	@Query("From Documento d WHERE d.iddocumento = :iddocumento")
	Documento findOneByIddocumento(@Param("iddocumento") Integer iddocumento);
	
}
