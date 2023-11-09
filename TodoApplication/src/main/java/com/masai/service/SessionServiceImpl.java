package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entities.Session;
import com.masai.entities.User;
import com.masai.exception.UserNotFoundException;
import com.masai.repository.SessionRepository;

@Service
public class SessionServiceImpl implements SessionService {
	
	@Autowired
	SessionRepository sessionRepo;

	@Override
	public Optional<Session> getSessionByUser(User user) {
		return sessionRepo.findByUser(user);
	}

	@Override
	public Session createSession(Session session) {
		return sessionRepo.save(session);
	}

	@Override
	public void deleteSessionById(Integer sessionId) {
		sessionRepo.findById(sessionId).orElseThrow(()->new UserNotFoundException("Invalid sesisonId"));
		sessionRepo.deleteById(sessionId);
	}

	@Override
	public Optional<Session> getSessionById(Integer sessionId) {
		return sessionRepo.findById(sessionId);
	}
	
	@Override
	public void deleteSession(Session session) {
		sessionRepo.delete(session);
	}

	@Override
	public String updateSession(Session session) {
		 sessionRepo.save(session);
		 return "Updated";
	}

}
