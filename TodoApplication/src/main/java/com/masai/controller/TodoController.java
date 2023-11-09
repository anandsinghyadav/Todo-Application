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
import com.masai.entities.Todos;
import com.masai.entities.User;
import com.masai.exception.UserNotFoundException;
import com.masai.service.SessionService;
import com.masai.service.TodoService;
import com.masai.service.UserService;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "*")
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/add/{userId}")
	public ResponseEntity<String> createTodo(@PathVariable Integer userId, @RequestBody Todos todo){
		
		User user = userService.getUserById(userId);
		todo.setUser(user);
		return new ResponseEntity<String>(todoService.createTodos(todo), HttpStatus.CREATED);
	}
	
	@PostMapping("/complete/{todoId}")
	public ResponseEntity<String> markTodoCompleted(@PathVariable Integer todoId){
		
		return new ResponseEntity<String>(todoService.markTodoCompleted(todoId), HttpStatus.CREATED);
	}
	
	@GetMapping("/under_user/{userId}")
	public ResponseEntity<List<Todos>> getTodosByUser(@PathVariable Integer userId){
		
		User user = userService.getUserById(userId);
		return new ResponseEntity<List<Todos>>(todoService.getTodoByUser(user), HttpStatus.OK);
		
	}
	
	@GetMapping("/completed/under_user/{userId}")
	public ResponseEntity<List<Todos>> getAllCompletedTodos(@PathVariable Integer userId){
		
		User user = userService.getUserById(userId);
		return new ResponseEntity<List<Todos>>(todoService.getAllCompletedTodos(user), HttpStatus.OK);
		
	}
	
	@GetMapping("/{todoId}")
	public ResponseEntity<Todos> getTodoByTodoId(@PathVariable Integer todoId){
		return new ResponseEntity<Todos>(todoService.getTodoById(todoId), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateTodo(@RequestBody Todos todo){
		return new ResponseEntity<String>(todoService.updateTodo(todo), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{todoId}")
	public ResponseEntity<String> deleteTodoByTodoId(@PathVariable Integer todoId){
		return new ResponseEntity<String>(todoService.deleteTodoById(todoId), HttpStatus.OK);
	}

}
