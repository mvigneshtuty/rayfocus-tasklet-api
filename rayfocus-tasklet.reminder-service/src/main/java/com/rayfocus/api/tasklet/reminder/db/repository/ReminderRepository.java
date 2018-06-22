package com.rayfocus.api.tasklet.reminder.db.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.rayfocus.api.tasklet.reminder.model.TaskReminder;

@EnableScan
public interface ReminderRepository extends CrudRepository<TaskReminder, String>{
	
}
