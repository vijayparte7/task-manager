package com.vijay.taskmanager.dao;

import java.util.List;

import com.vijay.taskmanager.dto.TaskUser;


public interface UserDao {
	void createUser(TaskUser user);
	void updateUser(TaskUser user);
	List<TaskUser> verify(TaskUser user);
	List<TaskUser> verifySignup(TaskUser user);
	List<TaskUser> verifyReset (TaskUser user);
	List<TaskUser>  verifyEmail(String email);
	TaskUser getUser(String email);
}
