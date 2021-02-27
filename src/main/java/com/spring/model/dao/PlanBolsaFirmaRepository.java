package com.spring.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.PlanBolsaFirma;

@Repository
public interface PlanBolsaFirmaRepository extends CrudRepository<PlanBolsaFirma, Integer>{

}
