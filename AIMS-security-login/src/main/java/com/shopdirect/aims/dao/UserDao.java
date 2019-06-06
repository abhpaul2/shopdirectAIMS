package com.shopdirect.aims.dao;

import java.util.List;

import com.shopdirect.aims.model.User;

public interface UserDao {

	void insertUser(User cus);
	void insertUsers(List<User> users);
	List<User> getAllUsers();
	User getUserById(String userId);
	void revokeUser(String userId);

}
