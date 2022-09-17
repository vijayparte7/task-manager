package com.vijay.taskmanager.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vijay.taskmanager.dao.UserDao;
import com.vijay.taskmanager.dto.TaskUser;


@Component
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

//	public UserDao getUserDao() {
//		return userDao;
//	}
//
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}

	@Override
	public void saveUser(TaskUser user) {
		userDao.createUser(user);
	}



	@Override
	public boolean verifyUser(TaskUser user) {
		List<TaskUser> result = userDao.verify(user);
		for (TaskUser taskUser : result) {
			return true;
		}

		return false;

	}

	@Override
	public boolean verifyUserSignup(TaskUser user) {
		List<TaskUser> result = userDao.verifySignup(user);
		for (TaskUser taskUser : result) {
			return false;
		}

		return true;
	}

	@Override
	public boolean verifyUserReset(TaskUser user) {
		List<TaskUser> result = userDao.verifyReset(user);
		for (TaskUser taskUser : result) {
			return true;
		}

		return false;
	}

	@Override
	public void updateUser(TaskUser user) {

		userDao.updateUser(user);
	}

	@Override
	public boolean verifyEmailReset(String email) {
		List<TaskUser> user = userDao.verifyEmail(email);
		for (TaskUser taskUser : user) {
			return true;
		}
		return false;
	}



	@Override
	public TaskUser getUser(String email) {
		return userDao.getUser(email);
	}

}
