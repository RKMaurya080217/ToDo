package com.todo.dto;

public class ToDoDTO {
	private String task;
	private String date;
	private boolean status;

	public ToDoDTO() {
	}
	public ToDoDTO(String task2, String date2, boolean status2) {
		// TODO Auto-generated constructor stub
		this.task=task2;
		this.date=date2;
		this.status=status2;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	} 

	@Override
	public String toString() {
		return "ToDoDTO [task=" + task + ", date=" + date + ", status=" + status + "]";
	}


}
