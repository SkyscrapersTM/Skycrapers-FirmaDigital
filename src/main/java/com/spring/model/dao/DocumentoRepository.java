package com.spring.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Documento;
import com.spring.entity.Role;
import com.spring.entity.Usuario;
@EnableJpaRepositories 
@Repository
public interface DocumentoRepository extends CrudRepository<Documento, Integer>{
/*
@Query(value = "SELECT * FROM Roles rol WHERE rol.name=\"CLI_FIRMANTE\"", nativeQuery = true)
public List<Role> listaRol();
	*/
	
	
}
