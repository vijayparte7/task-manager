package com.vijay.taskmanager.services;

import java.util.List;

import com.vijay.taskmanager.dto.Task;


public interface TaskService {
	void saveTask( Task task);
	void updateTask( Task task);
	void deleteTask( Task task);
	List<Task> listTask(String username);
}
