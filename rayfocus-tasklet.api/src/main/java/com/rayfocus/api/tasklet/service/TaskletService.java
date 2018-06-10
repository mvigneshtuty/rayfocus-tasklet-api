package com.rayfocus.api.tasklet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.rayfocus.api.tasklet.db.repository.TaskRepository;
import com.rayfocus.api.tasklet.http.HttpResponse;
import com.rayfocus.api.tasklet.model.Task;
import com.rayfocus.api.tasklet.model.TaskMetaData;

@Component
public class TaskletService {

	@Autowired
	TaskRepository taskRepository;

	public ResponseEntity<HttpResponse> saveTask(Task newTask) {
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
	
	public ResponseEntity<HttpResponse> getTaskByUserId(String userId){
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
