package com.shopdirect.aims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopdirect.aims.dao.UserDao;
import com.shopdirect.aims.model.User;
import com.shopdirect.aims.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;

	@Override
	public User getUserById(String userid) {
		
		//List<User> users = new ArrayList<User>();
		User user = userDao.getUserById(userid);
		System.out.println(user);
		//users.add(user);
		return user;
//		List<User> users = new ArrayList<User>();
//		User user = new User();
//		user.setUserId("ibm0507");
//		user.setUserName("Abhijit Paul");
//		user.setCloneId("ibm0305");
//		user.setStatus(true);
//		users.add(user);
//		return users;
	}

	@Override
	public void insertUser(User user) {
		userDao.insertUser(user);
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revokeUser(String userid) {
		userDao.revokeUser(userid);
		
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUsers();
	}

	@Override
	public void insertUsers(List<User> users) {
		userDao.insertUsers(users);		
	}

}
