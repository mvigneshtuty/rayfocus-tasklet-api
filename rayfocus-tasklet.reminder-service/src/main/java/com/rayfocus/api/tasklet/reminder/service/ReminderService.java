package com.rayfocus.api.tasklet.reminder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rayfocus.api.tasklet.reminder.db.repository.ReminderRepository;
import com.rayfocus.api.tasklet.reminder.model.TaskReminder;

@Component
public class ReminderService {
	
	@Autowired
	ReminderRepository reminderRepository;
	
	public void addReminder(TaskReminder reminder) {
		reminderRepository.save(reminder);
	}
}
