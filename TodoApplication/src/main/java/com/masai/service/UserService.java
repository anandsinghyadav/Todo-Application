package com.masai.service;

import java.util.List;
import java.util.Optional;

import com.masai.entities.User;

public interface UserService {
	
	public User addUser(User user);
	
	public User getUserById(Integer userId);
	
	public List<User> getUserByName(String name);
	
	public String login(String username, String password);
	
	public List<User> getAllUser();
	
	public String updateUser(User user);
	
	public String deleteUserByUserId(Integer userId);
	
	public String logout(Integer userId);
	
	public User findUserByUsername(String username);

}
