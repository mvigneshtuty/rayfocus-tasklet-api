package com.rayfocus.tasklet.api;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.rayfocus.tasklet.api.controller.TaskletController;
import com.rayfocus.tasklet.api.http.HttpResponse;
import com.rayfocus.tasklet.api.model.SubTask;
import com.rayfocus.tasklet.api.model.Task;
import com.rayfocus.tasklet.api.model.TaskMetaData;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public final class TaskletRepositoryTests {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Autowired
	private TaskletController taskletController;

	@Before
	public void setUp() throws InterruptedException {
		boolean isTableActive = false;
		amazonDynamoDB.createTable(new DynamoDBMapper(amazonDynamoDB).generateCreateTableRequest(Task.class)
				.withProvisionedThroughput(new ProvisionedThroughput(2L, 2L)));
		logger.info("waiting for Tasks table to be Active...");
		while (!isTableActive) {
			if (amazonDynamoDB.describeTable("Tasks").getTable().getTableStatus().equals("ACTIVE")) {
				isTableActive = true;
				logger.info("~~~ Tasks table is Active now ~~~");
			}
		}
	}

	@Test
	public void testSaveTask() {
		logger.info("Saving the taskable Items");
		
		List<SubTask> subTasks = new ArrayList<SubTask>(
				Arrays.asList(new SubTask("Vegetables", "INCOMPLETE"), new SubTask("Fruits", "COMPLETE")));
		Task newTask = new Task("145", "Buy Groceries", "Active", subTasks, "VigneshM@taskletAPI.com", "02-JUN-18");

		ResponseEntity<HttpResponse> response = taskletController.saveTask(newTask);
		
		assertTrue("Task saved successfully.", response.getBody().getStatusCode() == "200");
		assertTrue("Task Id matches", ((TaskMetaData) response.getBody().getResponseData()).getTaskId() == "145");
		logger.info("Save task done");
	}
}
