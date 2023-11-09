package com.masai.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entities.Session;
import com.masai.entities.User;
import com.masai.exception.UserNotFoundException;
import com.masai.service.SessionService;
import com.masai.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SessionService sessionService;
	
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user){
		return new ResponseEntity<User>(userService.addUser(user), HttpStatus.CREATED);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@RequestParam Integer sessionId, @PathVariable Integer userId){
		Optional<Session> sessionByUser = sessionService.getSessionByUser(userService.getUserById(userId));
		if(sessionByUser.get().getSessionId()!=sessionId) {
			throw new UserNotFoundException("You're not logged In, Please log in first.");
		}
		return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.CREATED);
	}

	@GetMapping("/user_by_name/{name}")
	public ResponseEntity<List<User>> getUsersByName(@PathVariable String name){
		return new ResponseEntity<List<User>>(userService.getUserByName(name), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username){
		return new ResponseEntity<User>(userService.findUserByUsername(username), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login/{username}/{password}")
	public ResponseEntity<String> login(@PathVariable String username, @PathVariable String password){
		return new ResponseEntity<String>(userService.login(username, password), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateUserDetails(@RequestParam Integer sessionId, @RequestBody User user){
		
		Optional<Session> sessionByUser = sessionService.getSessionByUser(user);
		if(sessionByUser.get().getSessionId()!=sessionId) {
			throw new UserNotFoundException("You're not logged In, Please log in first.");
		}
		
		return new ResponseEntity<String>(userService.updateUser(user), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUserById(@RequestParam Integer sessionId, @PathVariable Integer userId){
		
		Optional<Session> sessionByUser = sessionService.getSessionByUser(userService.getUserById(userId));
		if(sessionByUser.get().getSessionId()!=sessionId) {
			throw new UserNotFoundException("You're not logged In, Please log in first.");
		}
		
		return new ResponseEntity<String>(userService.deleteUserByUserId(userId), HttpStatus.OK);
	}
	
	@DeleteMapping("/logout/{sessionId}")
	public ResponseEntity<String> logout(@PathVariable Integer sessionId){
		return new ResponseEntity<String>(userService.logout(sessionId), HttpStatus.OK);
	}
}
