package com.spring.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.entity.Trabajador;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer>{
	
	/*@Query("select distinct(t1.nombre) , t1.empresa_idempresa from users t1 inner join empresa t2 where t1.empresa_idempresa like = :var_parm")
	public List<Trabajador> listarPorEmpresa(@Param(":var_parm") String idempresa);*/
}
