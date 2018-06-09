package com.rayfocus.tasklet.api.db.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.rayfocus.tasklet.api.model.Task;

@EnableScan
public interface TaskRepository extends CrudRepository<Task, String> {
	List<Task> findByUserId(String userId);
}
