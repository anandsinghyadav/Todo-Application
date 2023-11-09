package com.masai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public List<User> findByName(String name);
	
	public Optional<User> findByUsernameAndPassword(String username, String password);
	
	public User findByUsername(String username);

}
