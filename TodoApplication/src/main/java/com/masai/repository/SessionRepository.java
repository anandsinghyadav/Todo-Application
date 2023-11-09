package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entities.Session;
import com.masai.entities.User;

public interface SessionRepository extends JpaRepository<Session, Integer> {
	
	public Optional<Session> findByUser(User user);

}
