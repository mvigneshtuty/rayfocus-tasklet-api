package com.rayfocus.api.tasklet.reminder.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@DynamoDBTable(tableName="task_reminders")
public class TaskReminder {
	
	private String taskId;
	private String reminderStartDate;
	private String reminderEndDate;
	private String reminderTimeWithSeconds;
	private String MeridiemCode;
	
	@JsonCreator
	public TaskReminder(@JsonProperty("task_id") String taskId,
			            @JsonProperty("reminder_start_date") String reminderStartDate,
			            @JsonProperty("reminder_end_date") String reminderEndDate,
			            @JsonProperty("reminder_time") String reminderTimeWithSeconds,
			            @JsonProperty("meridiem_code") String MeridiemCode) {
		this.taskId = taskId;
		this.reminderStartDate = reminderStartDate;
		this.reminderEndDate = reminderEndDate;
		this.reminderTimeWithSeconds = reminderTimeWithSeconds;
		this.MeridiemCode = MeridiemCode;
	}
	
	
}

