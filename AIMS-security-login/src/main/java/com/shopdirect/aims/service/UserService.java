package com.shopdirect.aims.service;

import com.shopdirect.aims.model.User;

public interface UserService {

	User getUserById(String userid);
	User getCloneById(String userid);	
	void insertUser(User user);
	void updateUser(User user);
	boolean revokeUser(String userid);
	//List<User> getAllUser();
	//void insertUsers(List<User> users);


}
