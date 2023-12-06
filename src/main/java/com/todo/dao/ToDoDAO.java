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
	private ToDoDTO todoFetched;
	private String task;
	private boolean status;
	private String date;
	private PreparedStatement pstmt;
	private List<ToDoDTO> tasks;

	private String insert = "INSERT INTO todo (task, date, status) VALUES (?, ?, ?)";
	private String fetch = "SELECT * FROM todo";
	private String fetchcompleted = "SELECT * FROM todo where status='1'";
	private String fetchincompleted = "SELECT * FROM todo where status='0'";
	private String update;
	private String delete;
	private int rowsAffected = 0;

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydocument", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public ToDoDTO addTask(ToDoDTO tododto) {
		task = tododto.getTask();
		date = tododto.getDate();
		status = tododto.isStatus();
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
		tasks = new ArrayList<>();

		try {
			PreparedStatement pstmt = getConnection().prepareStatement(fetch);

//			System.out.println("\nConnection Created\n");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				todoFetched = new ToDoDTO();
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
				todoFetched = new ToDoDTO();
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
		tasks = new ArrayList<>();

		try {
			PreparedStatement pstmt = getConnection().prepareStatement(fetchincompleted);

			System.out.println("\nConnection Created for getIncompletedTask\n");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				todoFetched = new ToDoDTO();
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
		try {
			pstmt = getConnection().prepareStatement(delete);
			rowsAffected = pstmt.executeUpdate();

		} catch (SQLException e) {
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
		try {
			pstmt = getConnection().prepareStatement(update);

			rowsAffected = pstmt.executeUpdate();

		} catch (SQLException e) {
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
