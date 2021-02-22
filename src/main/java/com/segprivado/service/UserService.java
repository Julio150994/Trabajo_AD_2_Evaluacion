package com.segprivado.service;

import com.segprivado.entity.UserRole;


public interface UserService {
	
	public abstract UserRole findUser(int id);
	public abstract UserRole updateProfile(UserRole user);
}
