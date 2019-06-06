package com.shopdirect.aims.service;

import java.util.List;

import com.shopdirect.aims.model.User;

public interface UserService {

	User getUserById(String userid);
	void insertUser(User user);
	void updateUser(User user);
	void revokeUser(String userid);
	List<User> getAllUser();
	void insertUsers(List<User> users);


}
