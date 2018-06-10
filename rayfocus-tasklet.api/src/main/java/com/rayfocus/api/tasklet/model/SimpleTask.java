package com.rayfocus.api.tasklet.model;

public class SimpleTask {

	private final long taskId;
	private final String taskName;

	public SimpleTask(long taskId, String taskName) {
		this.taskId = taskId;
		this.taskName = taskName;
	}

	public long getTaskId() {
		return taskId;
	}

	public String getTaskName() {
		return taskName;
	}

}
