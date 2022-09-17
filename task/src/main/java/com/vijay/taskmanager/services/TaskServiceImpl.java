package com.vijay.taskmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijay.taskmanager.dao.TaskDao;
import com.vijay.taskmanager.dto.Task;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskDao taskDao;

//	public TaskDao getTaskDao() {
//		return taskDao;
//	}
//
//	public void setTaskDao(TaskDao taskDao) {
//		this.taskDao = taskDao;
//	}

	@Override
	public void saveTask(Task task) {
		taskDao.create(task);
	}

	@Override
	public void updateTask(Task task) {
		taskDao.update(task);

	}

	@Override
	public void deleteTask(Task task) {
		taskDao.delete(task);

	}

	@Override
	public List<Task> listTask(String username) {
		List<Task> tasks = taskDao.listTask(username);
		return tasks;
	}

}
