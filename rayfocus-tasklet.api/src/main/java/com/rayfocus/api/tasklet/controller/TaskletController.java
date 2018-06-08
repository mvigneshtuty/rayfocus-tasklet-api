package com.rayfocus.api.tasklet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rayfocus.api.tasklet.db.repository.TaskRepository;
import com.rayfocus.api.tasklet.http.HttpResponse;
import com.rayfocus.api.tasklet.model.Task;
import com.rayfocus.api.tasklet.model.TaskMetaData;

@RestController
@RequestMapping("/api/v1/tasklet")
public class TaskletController {

	@Autowired
	TaskRepository taskRepository;

	@PostMapping("/task/create")
	public ResponseEntity<HttpResponse> saveTask(@RequestBody Task newTask) {
		Task savedTask = taskRepository.save(newTask);

		TaskMetaData taskMetaData = null;
		if (savedTask == null) {
			taskMetaData = new TaskMetaData(newTask.getTaskId(), newTask.getTaskName());
			return new ResponseEntity<HttpResponse>(
					new HttpResponse("500", "ERROR", "Task creation failed", taskMetaData),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		taskMetaData = new TaskMetaData(savedTask.getTaskId(), savedTask.getTaskName());
		return new ResponseEntity<HttpResponse>(
				new HttpResponse("200", "SUCCESS", "Task created successfully", taskMetaData), HttpStatus.OK);

	}

	@GetMapping("/task/read/{userId}")
	public ResponseEntity<HttpResponse> getTasksByUserId(@PathVariable("userId") String userId) {
		List<Task> tasks = taskRepository.findByUserId(userId);
		if (tasks == null) {
			return new ResponseEntity<HttpResponse>(
					new HttpResponse("500", "ERROR", "Error fetching task details", tasks),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<HttpResponse>(new HttpResponse("200", "SUCCESS", "Task Details", tasks),
				HttpStatus.OK);
	}
}
