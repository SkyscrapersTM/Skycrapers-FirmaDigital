package com.spring.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Empresa;

@Repository
public interface EmpresaDAO extends JpaRepository<Empresa, Integer>{

}
