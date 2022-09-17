package com.vijay.taskmanager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vijay.taskmanager.dto.Task;

public interface TaskRepo extends JpaRepository<Task, Integer> {
		List<Task> findByUsername(String username);
}
