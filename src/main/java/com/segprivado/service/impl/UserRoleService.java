package com.segprivado.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.segprivado.entity.UserRole;
import com.segprivado.repository.UserRoleRepository;
import com.segprivado.service.UserService;


@Service("userRoleService")
public class UserRoleService implements UserDetailsService, UserService {
	
	@Autowired
	@Qualifier("userRoleRepository")
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public com.segprivado.entity.UserRole registerUser(com.segprivado.entity.UserRole user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);//establecer usuario como activado
		user.setRole(user.getRole());//para añadir un rol a la base de datos a partir del formulario
		return userRoleRepository.save(user);//permitirnos registrar el usuario
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.segprivado.entity.UserRole user = userRoleRepository.findByUsername(username);//recupera un objeto de la entidad
		UserBuilder register = null;
		
		if(user != null) { //comprobamos que esté deshabilitada la sesión
			register = User.withUsername(username);
			register.disabled(false);
			register.password(user.getPassword());
			register.authorities(new SimpleGrantedAuthority(user.getRole()));//permisos para ADMIN, PACIENTE, MÉDICO O VISITANTE
		}
		else
			throw new UsernameNotFoundException("User not found!");
		
		return register.build();
	}
	
	/*--------Métodos para el perfil del usuario con su id------------*/
	@Override
	public UserRole findUser(int id) {
		return userRoleRepository.findById(id).orElse(null);
	}
	
	@Override
	public UserRole updateProfile(UserRole user) {
		return userRoleRepository.save(user);
	}
}
