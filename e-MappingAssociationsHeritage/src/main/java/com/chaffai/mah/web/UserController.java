package com.chaffai.mah.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.chaffai.mah.entities.User;
import com.chaffai.mah.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/users/{username}")
	public User user(@PathVariable(name ="username") String userName) {
		User u = service.findUserByUserName(userName);

		return u;
	}
}
