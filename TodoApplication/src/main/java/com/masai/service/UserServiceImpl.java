package com.masai.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entities.Session;
import com.masai.entities.User;
import com.masai.exception.UserNotFoundException;
import com.masai.repository.SessionRepository;
import com.masai.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	SessionRepository sessionRepo;
	
	
	@Override
	public User addUser(User user) {
		return userRepo.save(user);
	}


	@Override
	public User getUserById(Integer userId) {
		return userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("Invalid User Id"));
	}


	@Override
	public List<User> getUserByName(String name) {
		List<User> findByName = userRepo.findByName(name);
		if(findByName.isEmpty()) {
			throw new UserNotFoundException("No user available");
		}else {
			return findByName;
		}
			
	}


	@Override
	public String login(String username, String password) {
		User user = userRepo.findByUsernameAndPassword(username, password).orElseThrow(()->new UserNotFoundException("Invalid username and password"));
        
        if(sessionService.getSessionByUser(user).isPresent()) {
        	return "Already Logged In";
        }else {
        	Session session  = new Session();
            session.setLoginDateTime(LocalDateTime.now());
            session.setUniqueCode(getUniqueId());
            session.setUser(user);
            user.setSession(session);
            sessionService.createSession(session);
            userRepo.save(user);
            return "Logged In successfully.";
        	
        }
		
	}
	
	public String getUniqueId() {
		Supplier<String> randomId = ()->UUID.randomUUID().toString();
		return randomId.get().replaceAll("-", "");
	}


	@Override
	public List<User> getAllUser() {
		  List<User> findAll = userRepo.findAll();
		  if(findAll.size()==0) {
			  throw new UserNotFoundException("No user available.");
		  }
		  return findAll;
	}


	@Override
	public String updateUser(User user) {
		userRepo.save(user);
		return "User updated successfully.";
	}


	@Override
	public String deleteUserByUserId(Integer userId) {
		 getUserById(userId);
		 userRepo.deleteById(userId);
		 return "Deleted successfully.";
		 
	}

	@Override
	public String logout(Integer userId) {
		User user = getUserById(userId);
		Optional<Session> sessionOpt = sessionService.getSessionByUser(user);
		
		if(sessionOpt.isPresent()) {
			Session session = sessionOpt.get();
			session.setUser(null);
			user.setSession(null);
			sessionRepo.delete(session);
		}else {
			throw new UserNotFoundException("Already Logged Out");
		}
		
		Optional<Session> optSession = sessionService.getSessionById(sessionOpt.get().getSessionId());
		if(optSession.isPresent()) {
			throw new UserNotFoundException("Something Went wrong, couldn't logout.");
		}
		return "Logged out successfully.";
		
	}


	@Override
	public User findUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
