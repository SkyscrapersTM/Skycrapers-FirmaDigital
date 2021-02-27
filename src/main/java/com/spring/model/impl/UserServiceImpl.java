package com.spring.model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.entity.Usuario;
import com.spring.model.dao.UserDAO;
import com.spring.model.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDAO usuarioDAO;
	
	@Override
	public Usuario findByUsername(String username) {
		return usuarioDAO.findByUsername(username);
	}

	@Override
	public Usuario registrar(Usuario u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return usuarioDAO.save(u);
	}

}
