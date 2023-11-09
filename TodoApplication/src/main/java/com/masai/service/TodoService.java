package com.masai.service;

import java.util.List;

import com.masai.entities.Todos;
import com.masai.entities.User;

public interface TodoService {
	
	public String createTodos(Todos todo);
	
	public List<Todos> getTodoByUser(User user);
	
	public Todos getTodoById(Integer todoId);
	
	public String updateTodo(Todos todo);
	
	public String deleteTodoById(Integer todoId);
	
	public String markTodoCompleted(Integer todoId);
	
	public List<Todos> getAllCompletedTodos(User user);

}
