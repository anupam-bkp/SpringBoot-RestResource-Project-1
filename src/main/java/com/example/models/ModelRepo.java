package com.example.models;

import java.util.ArrayList;
import java.util.List;

public class ModelRepo {

	public static List<User> getUsers(){
		
		final List<User> users = new ArrayList<>();
		
		User user = null;
		
		user = new User("Anupam", "anu@123", "anupam@gmail.com");
		user.setId(1);
		users.add(user);
		
		user = new User("Pawan", "pawan@123", "pawan@gmail.com");
		user.setId(2);
		users.add(user);
		
		user = new User("Amit", "Amit@123", "Amit@gmail.com");
		user.setId(3);
		users.add(user);
		
		return users;
	}
	
}
