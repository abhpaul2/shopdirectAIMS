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
		User user = userDao.getUserById(userid);
		System.out.println(user);
		return user;
	}
	
	@Override
	public User getCloneById(String userid) {
		User user = userDao.getCloneById(userid);
		System.out.println("Clone " +user);
		return user;
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
	public boolean revokeUser(String userid) {
		return userDao.revokeUser(userid);
		
	}

	/*
	 * @Override public List<User> getAllUser() { return userDao.getAllUsers(); }
	 * 
	 * @Override public void insertUsers(List<User> users) {
	 * userDao.insertUsers(users); }
	 */
}
