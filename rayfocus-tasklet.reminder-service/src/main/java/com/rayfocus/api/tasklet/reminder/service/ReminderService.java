package com.rayfocus.api.tasklet.reminder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.rayfocus.api.tasklet.reminder.db.repository.ReminderRepository;
import com.rayfocus.api.tasklet.reminder.http.HttpResponse;
import com.rayfocus.api.tasklet.reminder.model.TaskReminder;

@Component
public class ReminderService {
	
	@Autowired
	ReminderRepository reminderRepository;
	
	public ResponseEntity<HttpResponse> addReminder(TaskReminder reminder) {
		TaskReminder savedReminder = reminderRepository.save(reminder);
		if(savedReminder == null) {
			return new ResponseEntity<HttpResponse>(new HttpResponse("500", "ERROR", "Reminder Addition Failed", savedReminder), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<HttpResponse>(new HttpResponse("200", "SUCCESS", "Reminder Added Successfully", savedReminder), HttpStatus.OK);
	}
	
	public ResponseEntity<HttpResponse> getReminderByTaskId(String taskId) {
		List<TaskReminder> remindersList = reminderRepository.findByTaskId(taskId);
		if(remindersList == null) {
			return new ResponseEntity<HttpResponse>(new HttpResponse("500", "ERROR", "Error Fecthing Reminders", remindersList), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<HttpResponse>(new HttpResponse("200", "SUCCESS", "Reminder Details", remindersList), HttpStatus.OK);
	}
}
