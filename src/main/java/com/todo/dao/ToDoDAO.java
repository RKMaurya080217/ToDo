package com.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.todo.dto.ToDoDTO;

public class ToDoDAO {
	private String task;
	private boolean status;
	private String date;
	private PreparedStatement pstmt;

	String insert = "INSERT INTO todo (task, date, status) VALUES (?, ?, ?)";
	String fetch = "SELECT * FROM todo";
	String fetchcompleted = "SELECT * FROM todo where status='1'";
	String fetchincompleted = "SELECT * FROM todo where status='0'";
	String update;
	String delete;

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydocument", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public ToDoDTO addTask(ToDoDTO tododto) {
		task = tododto.getTask();
		date = tododto.getDate();
		status = tododto.isStatus();
		int rowsAffected = 0;
		try {
			pstmt = getConnection().prepareStatement(insert);
			pstmt.setString(1, task);
			pstmt.setString(2, date);
			pstmt.setBoolean(3, status);

			rowsAffected = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (rowsAffected > 0) {
			System.out.println(task + " Task added successfully!");
			return tododto;
		} else {
			System.out.println(task + " Failed to add task.");
			return null;
		}
	}

	public List<ToDoDTO> getAllTasks() {
		List<ToDoDTO> tasks = new ArrayList<>();

		try {
			PreparedStatement pstmt = getConnection().prepareStatement(fetch);

			System.out.println("\nConnection Created\n");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				/*
				 * String task = rs.getString("task"); String date = rs.getString("date");
				 * boolean status = rs.getBoolean("status");
				 * 
				 * ToDoDTO todo = new ToDoDTO(task, date, status); tasks.add(todo);
				 */
				ToDoDTO todoFetched = new ToDoDTO();
				todoFetched.setTask(rs.getString("task"));
				todoFetched.setDate(rs.getString("date"));
				todoFetched.setStatus(rs.getBoolean("status"));

				tasks.add(todoFetched);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasks;
	}

	public List<ToDoDTO> getCompletedTask() {
		List<ToDoDTO> tasks = new ArrayList<>();

		try {
			PreparedStatement pstmt = getConnection().prepareStatement(fetchcompleted);

			System.out.println("\nConnection Created for getCompletedTask\n");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ToDoDTO todoFetched = new ToDoDTO();
				todoFetched.setTask(rs.getString("task"));
				todoFetched.setDate(rs.getString("date"));
				todoFetched.setStatus(rs.getBoolean("status"));

				tasks.add(todoFetched);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasks;
	}

	public List<ToDoDTO> getIncompletedTask() {
		List<ToDoDTO> tasks = new ArrayList<>();

		try {
			PreparedStatement pstmt = getConnection().prepareStatement(fetchincompleted);

			System.out.println("\nConnection Created for getIncompletedTask\n");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ToDoDTO todoFetched = new ToDoDTO();
				todoFetched.setTask(rs.getString("task"));
				todoFetched.setDate(rs.getString("date"));
				todoFetched.setStatus(rs.getBoolean("status"));

				tasks.add(todoFetched);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasks;
	}

	public boolean deleteTask(String task) {
		delete = "DELETE FROM  todo WHERE task= '" + task + "'";
		int rowsAffected = 0;
		try {
			pstmt = getConnection().prepareStatement(delete);
			rowsAffected = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (rowsAffected > 0) {
			System.out.println(task + " Task deleted successfully!");
			return true;
		} else {
			System.out.println(task + " Failed to delete.");
			return false;
		}

	}

	public ToDoDTO updateTask(String task, ToDoDTO tododto) {
		update = "UPDATE todo SET task = '" + tododto.getTask() + "', date = '" + tododto.getDate() + "', status = "
				+ tododto.isStatus() + " WHERE task ='" + task + "'";
		// System.out.println("\n"+update+"\n");
		int rowsAffected = 0;
		try {
			pstmt = getConnection().prepareStatement(update);

			rowsAffected = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (rowsAffected > 0) {
			System.out.println(task + " Task updated successfully!");
			return tododto;
		} else {
			System.out.println(task + " Failed to update.");
			return null;
		}

	}

}
