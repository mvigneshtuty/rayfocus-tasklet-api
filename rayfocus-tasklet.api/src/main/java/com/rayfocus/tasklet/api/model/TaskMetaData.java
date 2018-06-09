package com.rayfocus.tasklet.api.model;

public class TaskMetaData {
	private final String taskId;
	private final String taskName;

	public TaskMetaData(String taskId, String taskName) {
		this.taskId = taskId;
		this.taskName = taskName;
	}

	public String getTaskId() {
		return taskId;
	}

	public String getTaskName() {
		return taskName;
	}

}
