package com.rayfocus.api.tasklet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rayfocus.api.tasklet.http.HttpResponse;
import com.rayfocus.api.tasklet.model.Task;
import com.rayfocus.api.tasklet.service.TaskletService;

@RestController
@RequestMapping("/tasklet")
public class TaskletController {

	@Autowired
	TaskletService taskletService;

	@PostMapping("/task")
	public ResponseEntity<HttpResponse> saveTask(@RequestBody Task newTask,
			@RequestParam(value = "version", required = false) String apiVersion) {
		return taskletService.saveTask(newTask);
	}

	@GetMapping("/task/{userid}")
	public ResponseEntity<HttpResponse> getTasksByUserId(@PathVariable("userid") String userId,
			@RequestParam(value = "version", required = false) String apiVersion) {
		return taskletService.getTaskByUserId(userId);
	}
	
	@DeleteMapping("/admin/task/truncate")
	public ResponseEntity<HttpResponse> deleteAllTasks(@RequestParam(value = "version", required = false) String apiVersion) {
		return taskletService.deleteAllTasks();
	}
}
