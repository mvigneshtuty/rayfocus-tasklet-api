package com.rayfocus.api.tasklet.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@DynamoDBDocument
public class SubTask {
	
	private String taskItem;
	private String status;

	@JsonCreator
	public SubTask(@JsonProperty("task_item") String taskItem, 
			       @JsonProperty("status") String status) {
		this.taskItem = taskItem;
		this.status = status;
	}

	public SubTask() 
	{
		// default constructor
	}
	
	/** Getters */
	@DynamoDBAttribute(attributeName = "task_item")
	public String getTaskItem() {
		return taskItem;
	}

	@DynamoDBAttribute(attributeName = "status")
	public String getStatus() {
		return status;
	}

	/** Setters */
	public void setTaskItem(String taskItem) {
		this.taskItem = taskItem;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}	
