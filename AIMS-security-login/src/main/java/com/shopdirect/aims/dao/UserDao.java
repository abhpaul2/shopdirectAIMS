package com.shopdirect.aims.dao;

import com.shopdirect.aims.model.User;

public interface UserDao {

	void insertUser(User cus);
	User getUserById(String userId);
	User getCloneById(String userId);
	boolean revokeUser(String userId);

	//void insertUsers(List<User> users);
	//List<User> getAllUsers();

}
