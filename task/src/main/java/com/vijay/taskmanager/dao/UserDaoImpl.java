package com.vijay.taskmanager.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vijay.taskmanager.dto.TaskUser;
import com.vijay.taskmanager.repo.TaskUserRepo;

@Component
public class UserDaoImpl implements UserDao {
	@Autowired
	private TaskUserRepo userRepo;
	
	@Override
	public void createUser(TaskUser user) {
		userRepo.save(user);
	}
	@Override
	public List<TaskUser> verify(TaskUser user) {
		List<TaskUser> userList = userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		return userList;
	}
	@Override
	public List<TaskUser> verifySignup(TaskUser user) {
		List<TaskUser> userList = userRepo.findByUsername(user.getUsername());
		return userList;
	}
	@Override
	public List<TaskUser> verifyReset(TaskUser user) {
		List<TaskUser> userList = userRepo.findByUsernameAndEmail(user.getUsername(), user.getEmail());
		return userList;
	}
	@Override
	public void updateUser(TaskUser user) {
		userRepo.save(user);
	}
	@Override
	public List<TaskUser> verifyEmail(String email) {
		List<TaskUser> userList = userRepo.findByEmail(email);
		return userList;

	}
	@Override
	public TaskUser getUser(String email) {
		List<TaskUser> findByEmail = userRepo.findByEmail(email);
		for (TaskUser taskUser : findByEmail) {
			return taskUser;
		}
		return null;
	}
}
