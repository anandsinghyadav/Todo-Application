package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entities.Todos;
import com.masai.entities.User;
import com.masai.entities.WorkStatus;
import com.masai.exception.TodoNotFoundException;
import com.masai.repository.TodosRepository;

@Service
public class TodoServiceImpl implements TodoService{
	
	@Autowired
	TodosRepository todoRepo;
	
	@Autowired
	UserService userService;


	@Override
	public String createTodos(Todos todo) {
		User user = todo.getUser();
		user.getTodoList().add(todo);
		userService.updateUser(user);
        todoRepo.save(todo);
        return "Created Successfully";
	}

	@Override
	public List<Todos> getTodoByUser(User user) {
		List<Todos> list = todoRepo.findByUser(user);
		if(list.isEmpty()) {
			throw new TodoNotFoundException("No todo found for user: "+user.getName());
		}
		return list.stream().filter(todo->todo.getStatus()==WorkStatus.UNCOMPLETED).toList();
	}

	@Override
	public Todos getTodoById(Integer todoId) {
		return todoRepo.findById(todoId).orElseThrow(()->new TodoNotFoundException("Invalid Todo Id"));
	}

	@Override
	public String updateTodo(Todos todo) {
		getTodoById(todo.getTodoId());
		todoRepo.save(todo);
		return "Todo udpated successfully";
	}

	@Override
	public String deleteTodoById(Integer todoId) {
		 Todos todo = getTodoById(todoId);
		 User user = todo.getUser();
		 user.getTodoList().remove(todo);
		 todoRepo.deleteById(todoId);
		 return "deleted successfully.";
	}

	@Override
	public String markTodoCompleted(Integer todoId) {
	     Todos todos = getTodoById(todoId);
	     todos.setStatus(WorkStatus.COMPLETED);
	     todoRepo.save(todos);
	     return "Successfully marked as completed.";
	}

	@Override
	public List<Todos> getAllCompletedTodos(User user) {
		List<Todos> list = todoRepo.findByUser(user);
		if(list.isEmpty()) {
		    throw new TodoNotFoundException("No todo found for user: "+user.getName());
		}
		return list.stream().filter(todo->todo.getStatus()==WorkStatus.COMPLETED).toList();
	}

}
