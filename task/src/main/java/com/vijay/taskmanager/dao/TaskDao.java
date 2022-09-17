package com.vijay.taskmanager.dao;

import java.util.List;

import com.vijay.taskmanager.dto.Task;


public interface TaskDao {
	void create( Task task);
	void update( Task task);
	void delete( Task task);
	List<Task> listTask(String username);
}
