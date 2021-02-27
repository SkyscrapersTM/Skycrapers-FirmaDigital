package com.spring.model.service;

import com.spring.entity.Usuario;


public interface UserService {

	public Usuario findByUsername(String username);
	public Usuario registrar(Usuario u);
}
