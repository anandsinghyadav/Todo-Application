package com.masai.service;

import java.util.Optional;

import com.masai.entities.Session;
import com.masai.entities.User;

public interface SessionService {
	
    public Optional<Session> getSessionByUser(User user);
    
    public Optional<Session> getSessionById(Integer sessionId);
    
    public Session createSession(Session session);
    
    public void deleteSessionById(Integer sessionId);
    
    public void deleteSession(Session session);
    
    public String updateSession(Session session);
}
