package com.vijay.taskmanager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vijay.taskmanager.dto.TaskUser;

public interface TaskUserRepo extends JpaRepository<TaskUser, String> {
	List<TaskUser> findByUsernameAndPassword(String username,String password);
	List<TaskUser> findByUsernameAndEmail(String username,String email);
	List<TaskUser> findByUsername(String username);
	List<TaskUser> findByEmail(String email);
}
