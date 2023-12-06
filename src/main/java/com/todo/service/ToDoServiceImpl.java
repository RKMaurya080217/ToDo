package com.todo.service;

import java.util.ArrayList;
import java.util.List;

import com.todo.dao.ToDoDAO;
import com.todo.dto.ToDoDTO;

public class ToDoServiceImpl implements ToDoService {

	private ToDoDAO todoDAO;
	private List<ToDoDTO> list;

	@Override
	public ToDoDTO createTodo(ToDoDTO tododto) {
		todoDAO = new ToDoDAO();
		tododto = todoDAO.addTask(tododto);
		return tododto;
	}

	@Override
	public List<ToDoDTO> viewAllToDo() {
		todoDAO = new ToDoDAO();
		list = new ArrayList<ToDoDTO>();
		list = todoDAO.getAllTasks();
		return list;
	}

	@Override
	public List<ToDoDTO> getCompletedTask() {
		todoDAO = new ToDoDAO();
		list = new ArrayList<ToDoDTO>();
		list = todoDAO.getCompletedTask();
		return list;
	}

	@Override
	public List<ToDoDTO> getIncompletedTask() {
		todoDAO = new ToDoDAO();
		list = new ArrayList<ToDoDTO>();
		list = todoDAO.getIncompletedTask();
		return list;
	}

	@Override
	public boolean deleteTask(String task) {
		todoDAO = new ToDoDAO();
		boolean deleted = todoDAO.deleteTask(task);

		return deleted;
	}

	@Override
	public ToDoDTO updateToDo(String name, ToDoDTO tododto) {
		todoDAO = new ToDoDAO();
		tododto = todoDAO.updateTask(name, tododto);
		return tododto;
	}

}
