package com.spring.model.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.entity.Trabajador;

public interface TrabajadorDAOPag extends PagingAndSortingRepository<Trabajador, Integer>{

}
