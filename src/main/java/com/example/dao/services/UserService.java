package com.example.dao.services;

import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.models.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	public void addUser(final User user) {
		userDao.persistUser(user);
	}
	
	public List<User> getAllUsers(){
		return userDao.fetchAllUsers();
	}
	
	public User getUserByUserName(final String username) {
		return userDao.getUserByUsername(username);
	}
}
