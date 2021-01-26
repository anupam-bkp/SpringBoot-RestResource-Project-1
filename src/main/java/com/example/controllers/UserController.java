package com.example.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.services.UserService;
import com.example.models.User;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
//	@CrossOrigin(origins = "http://localhost:3000")
	@CrossOrigin(origins = "http://192.168.1.8:3000")
	@GetMapping("/users")
	public List<User> getUsers(){
		return userService.getAllUsers();
	}
	
//	@CrossOrigin(origins = "http://localhost:3000")
	@CrossOrigin(origins = "http://192.168.1.8:3000")
	@PostMapping("/users")
	public User addUser(@Valid @RequestBody User user) {
		
		System.out.println("=============Post User==============");
		System.out.println(user);
		
		userService.addUser(user);
		
		return user;
	}
	
//	@CrossOrigin(origins = "http://localhost:3000")
	@CrossOrigin(origins = "http://192.168.1.8:3000")
	@GetMapping("/user/{username}")
	public EntityModel<User> getUserHateos(@PathVariable String username) {
		
		final User userByUserName = userService.getUserByUserName(username);
		
		final EntityModel<User> userEntityModel = EntityModel.of(userByUserName); 
		
		final WebMvcLinkBuilder linkBuilder = 
					WebMvcLinkBuilder.linkTo(
							WebMvcLinkBuilder.methodOn(this.getClass()).getUsers());
		final Link link1 = linkBuilder.withRel("Users1");
		final Link link2 = linkBuilder.withRel("Users2");
		
//		userEntityModel.add(link);
		userEntityModel.add(link1, link2);
		
		return userEntityModel;
	}
	
//	@CrossOrigin(origins = "http://localhost:3000")
	@CrossOrigin(origins = "http://192.168.1.8:3000")
	@PostMapping("/userslist")
	public List<User> addUserList(@Valid @RequestBody List<User> users) {
		
		System.out.println("=============Post User==============");
		System.out.println(users);
		
		return users;
	}
	
//	@CrossOrigin(origins = "http://localhost:3000")
	@CrossOrigin(origins = "http://192.168.1.8:3000")
	@GetMapping("/users/auth/{username}/{password}")
	public boolean authenticate(@PathVariable String username, @PathVariable String password) {
		
		System.out.println("========= Authenticating ===============");
		
		System.out.println(username + " :: " + password);
		
		final User user = userService.getUserByUserName(username.trim());
		
		System.out.println("From user: " + user.getUsername() + " :: " + user.getPassword());
		
		boolean result = false;
		
		if(password.equals(user.getPassword())){
			result = true;
		}
		
		return result;
	}
	
}
