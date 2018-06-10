package com.rayfocus.api.tasklet.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@DynamoDBTable(tableName = "Tasks")
public class Task {

	private String taskId;
	private String taskName;
	private String taskStatus;
	private List<SubTask> taskContent;
	private String userId;
	private String createdOn;

	@JsonCreator
	public Task(@JsonProperty("task_id") String taskId, @JsonProperty("task_name") String taskName,
			@JsonProperty("task_status") String taskStatus, @JsonProperty("task_content") List<SubTask> taskContent,
			@JsonProperty("user_id") String userId, @JsonProperty("created_on") String createdOn) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskStatus = taskStatus;
		this.taskContent = taskContent;
		this.userId = userId;
		this.createdOn = createdOn;
	}

	public Task() 
	{
		// default constructor
	}

	/** Getters */
	@DynamoDBHashKey(attributeName = "task_id")
	public String getTaskId() {
		return taskId;
	}

	@DynamoDBAttribute(attributeName = "task_name")
	public String getTaskName() {
		return taskName;
	}
	
	@DynamoDBAttribute(attributeName = "task_status")
	public String getTaskStatus() {
		return taskStatus;
	}

	@DynamoDBAttribute(attributeName = "task_content")
	public List<SubTask> getTaskContent() {
		return taskContent;
	}

	@DynamoDBAttribute(attributeName = "user_id")
	public String getUserId() {
		return userId;
	}

	@DynamoDBAttribute(attributeName = "created_on")
	public String getCreatedOn() {
		return createdOn;
	}

	/** Setters */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public void setTaskContent(List<SubTask> taskContent) {
		this.taskContent = taskContent;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", taskStatus=" + taskStatus + ", taskContent="
				+ taskContent + ", userId=" + userId + ", createdOn=" + createdOn + "]";
	}

}
