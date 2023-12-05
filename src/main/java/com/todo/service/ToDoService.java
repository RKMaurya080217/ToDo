package com.todo.service;

import java.util.List;

import com.todo.dto.ToDoDTO;

public interface ToDoService {
	ToDoDTO createTodo(ToDoDTO tododto);
	//updateToDo();
	List<ToDoDTO>viewAllToDo();
	List<ToDoDTO>getCompletedTask();
	List<ToDoDTO>getIncompletedTask();
	boolean deleteTask(String name);
	//deleteToDo();
	
}
