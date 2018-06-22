package com.rayfocus.api.tasklet.reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rayfocus.api.tasklet.reminder.model.TaskReminder;
import com.rayfocus.api.tasklet.reminder.service.ReminderService;

@RestController
@RequestMapping(path = "/tasklet")
public class ReminderServiceController {
	
	@Autowired
	ReminderService reminderService;

	@PostMapping(path = "/reminder")
	public void addReminder(@RequestBody TaskReminder reminder,
			@RequestParam(value = "version", required = false) String apiVersion) {
		reminderService.addReminder(reminder);
	}
}
