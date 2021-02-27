package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.entity.MyUserDetails;
import com.spring.entity.Usuario;
import com.spring.model.dao.UserDAO;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired 
	private UserDAO usuarioDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioDAO.findByUsername(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		return new MyUserDetails(usuario);
	}

}
