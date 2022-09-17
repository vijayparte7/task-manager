package com.vijay.taskmanager.users.services;

import com.vijay.taskmanager.dto.TaskUser;

public interface UserService {
	void saveUser(TaskUser user);
	void updateUser(TaskUser user);
	boolean verifyUser(TaskUser user);
	boolean verifyUserSignup(TaskUser user);
	boolean verifyUserReset(TaskUser user);
	boolean verifyEmailReset(String email);
	TaskUser getUser(String email);
}
