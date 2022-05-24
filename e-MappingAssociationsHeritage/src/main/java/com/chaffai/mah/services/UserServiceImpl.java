package com.chaffai.mah.services;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.chaffai.mah.entities.Role;
import com.chaffai.mah.entities.User;
import com.chaffai.mah.repositories.RoleRepository;
import com.chaffai.mah.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;	

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public User addNewUser(User user) {
		user.setUserId(UUID.randomUUID().toString());
		//hasher le password avant d'enregistrer le user
		return userRepository.save(user);
	}

	@Override
	public Role addNewRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public User findUserByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Role findRoleByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		User user = findUserByUserName(username);
		Role role = findRoleByRoleName(roleName);
		
		if (user.getRoles()!=null) {
			//Ajouter le role a l'utilisateur
			user.getRoles().add(role);
			//Ajouter l'utilisateur au role
			role.getUsers().add(user);
		}		
		//userRepository.save(user);
	}

	@Override
	public User authenticate(String userName, String password) {
		User user = userRepository.findByUsername(userName);
		if (user == null)
			throw new RuntimeException("Bad Credentials");
		if (user.getPassword().equals(password)) {
			return user;
		}
		throw new RuntimeException("Bad Credentials");
	}

}
