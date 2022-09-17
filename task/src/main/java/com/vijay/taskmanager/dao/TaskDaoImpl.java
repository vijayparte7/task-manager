package com.vijay.taskmanager.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vijay.taskmanager.dto.Task;
import com.vijay.taskmanager.repo.TaskRepo;

@Repository
public class TaskDaoImpl implements TaskDao {

	@Autowired
	private TaskRepo repo;

	@Override
	public void create(Task task) {
		repo.save(task);
	}

	@Override
	public void update(Task task) {
		repo.save(task);
	}

	@Override
	public void delete(Task task) {
		repo.delete(task);
	}

	@Override
	public List<Task> listTask(String username) {
		List<Task> tasks = repo.findByUsername(username);
		return tasks;
	}

}
