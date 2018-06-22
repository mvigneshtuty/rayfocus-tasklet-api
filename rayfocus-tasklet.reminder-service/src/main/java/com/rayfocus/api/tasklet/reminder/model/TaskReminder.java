package com.rayfocus.api.tasklet.reminder.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
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
	
	public TaskReminder() {
		// default constructor
	}

	/** Getters */
	@DynamoDBHashKey(attributeName = "task_id")
	public String getTaskId() {
		return taskId;
	}

	@DynamoDBAttribute(attributeName="reminder_start_date")
	public String getReminderStartDate() {
		return reminderStartDate;
	}

	@DynamoDBAttribute(attributeName="reminder_end_date")
	public String getReminderEndDate() {
		return reminderEndDate;
	}

	@DynamoDBAttribute(attributeName="reminder_time")
	public String getReminderTimeWithSeconds() {
		return reminderTimeWithSeconds;
	}

	@DynamoDBAttribute(attributeName="meridiem_code")
	public String getMeridiemCode() {
		return MeridiemCode;
	}

	/** Setters */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setReminderStartDate(String reminderStartDate) {
		this.reminderStartDate = reminderStartDate;
	}

	public void setReminderEndDate(String reminderEndDate) {
		this.reminderEndDate = reminderEndDate;
	}

	public void setReminderTimeWithSeconds(String reminderTimeWithSeconds) {
		this.reminderTimeWithSeconds = reminderTimeWithSeconds;
	}

	public void setMeridiemCode(String meridiemCode) {
		MeridiemCode = meridiemCode;
	}

	@Override
	public String toString() {
		return "TaskReminder [taskId=" + taskId + ", reminderStartDate=" + reminderStartDate + ", reminderEndDate="
				+ reminderEndDate + ", reminderTimeWithSeconds=" + reminderTimeWithSeconds + ", MeridiemCode="
				+ MeridiemCode + "]";
	}
	
	
}

