package com.todo.controler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RestController;

import com.todo.dto.ToDoDTO;
import com.todo.service.ToDoServiceImpl;

@RestController
public class ToDoController {

	ToDoServiceImpl serviceimpl;
	List<ToDoDTO> tododto;
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ToDoController.class);

	@RequestMapping("/todo")
	@PostMapping
	public ResponseEntity<ToDoDTO> createTask(@RequestBody ToDoDTO tododto) {
		serviceimpl = new ToDoServiceImpl();
		tododto = serviceimpl.createTodo(tododto);
		return ResponseEntity.status(HttpStatus.CREATED).body(tododto);

	}

	@CrossOrigin
	@GetMapping("/alltask")
	public ResponseEntity<List<ToDoDTO>> getAllTask() {
		serviceimpl = new ToDoServiceImpl();
		logger.info("I'm inside getAllTask method");

		try {
			List<ToDoDTO> tododto = new ArrayList<ToDoDTO>();
			tododto = serviceimpl.viewAllToDo();
			return ResponseEntity.ok(tododto);
		} catch (Exception e) {
			logger.error("Error in getAllTask method", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/completedtask")
	public ResponseEntity<List<ToDoDTO>> getCompletedTask() {
		serviceimpl = new ToDoServiceImpl();
		logger.info("I'm inside getCompletedTask method");

		try {
			tododto = new ArrayList<ToDoDTO>();
			tododto = serviceimpl.getCompletedTask();
			return ResponseEntity.ok(tododto);
		} catch (Exception e) {
			logger.error("Error in getCompletedTask method", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/incompletedtask")
	public ResponseEntity<List<ToDoDTO>> getIncompletedTask() {
		serviceimpl = new ToDoServiceImpl();
		logger.info("I'm inside getIncompletedTask method");

		try {
			tododto = new ArrayList<ToDoDTO>();
			tododto = serviceimpl.getIncompletedTask();
			return ResponseEntity.ok(tododto);
		} catch (Exception e) {
			logger.error("Error in getIncompletedTask method", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/todo/{task}")
	public ResponseEntity<ToDoDTO> updateToDo(@PathVariable String task, @RequestBody ToDoDTO tododto) {
		serviceimpl = new ToDoServiceImpl();
		ToDoDTO updatedTask = serviceimpl.updateToDo(task, tododto);
		if (updatedTask != null) {
			return ResponseEntity.ok(updatedTask);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/todo/{task}")
	@PostMapping
	public ResponseEntity<Void> deleteTask(@PathVariable String task) {
		serviceimpl = new ToDoServiceImpl();
		// System.out.println("\n" + task + "\n");
		boolean deleted = serviceimpl.deleteTask(task);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}
}
