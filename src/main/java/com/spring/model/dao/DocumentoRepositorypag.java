package com.spring.model.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.entity.Documento;

public interface DocumentoRepositorypag extends PagingAndSortingRepository<Documento, Integer> {

}
