package com.rayfocus.api.tasklet.db.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.rayfocus.api.tasklet.model.Task;

@EnableScan
public interface TaskRepository extends CrudRepository<Task, String> {
	List<Task> findByUserId(String userId);
}
