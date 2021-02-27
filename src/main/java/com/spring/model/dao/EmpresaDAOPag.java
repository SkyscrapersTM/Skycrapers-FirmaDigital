package com.spring.model.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.entity.Empresa;

public interface EmpresaDAOPag extends PagingAndSortingRepository<Empresa, Integer> {

}
