package com.rayfocus.api.tasklet;

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
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.amazonaws.services.dynamodbv2.util.TableUtils.TableNeverTransitionedToStateException;
import com.rayfocus.api.tasklet.http.HttpResponse;
import com.rayfocus.api.tasklet.model.SubTask;
import com.rayfocus.api.tasklet.model.Task;
import com.rayfocus.api.tasklet.model.TaskMetaData;
import com.rayfocus.api.tasklet.service.TaskletService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public final class TaskletServiceTests {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Autowired
	private TaskletService taskletService;
	
	private static boolean taskTableExist = false;

	@Before
	public void setUp() throws TableNeverTransitionedToStateException, InterruptedException {
		if( ! taskTableExist ) {
			if(TableUtils.createTableIfNotExists(amazonDynamoDB, new DynamoDBMapper(amazonDynamoDB).generateCreateTableRequest(Task.class)
					.withProvisionedThroughput(new ProvisionedThroughput(2L, 2L)))) {
				logger.info("~~~ Waiting for Tasks table to be active ~~~");
				TableUtils.waitUntilActive(amazonDynamoDB, "Tasks");
			}
			else {
				logger.info("~~~ Tasks table already exists ~~~");
			}
			taskTableExist = true;
		}
	}

	@Test
	public void testSaveTask() {
		logger.info("Saving the taskable items");

		List<SubTask> subTasks = new ArrayList<SubTask>(
				Arrays.asList(new SubTask("Vegetables", "INCOMPLETE"), new SubTask("Fruits", "COMPLETE")));
		Task newTask = new Task("456", "Buy Groceries", "Active", subTasks, "users@taskletAPI.com", "11-JUN-2018");

		ResponseEntity<HttpResponse> response = taskletService.saveTask(newTask);

		assertTrue("Task saved successfully.", response.getBody().getStatusCode() == "200");
		assertTrue("Task Id matches", ((TaskMetaData) response.getBody().getResponseData()).getTaskId() == "456");
		logger.info("Save task done");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetTaskByUserId() {
		logger.info("Fetching the task items");

		ResponseEntity<HttpResponse> response = taskletService.getTaskByUserId("users@taskletAPI.com");

		assertTrue("Task fetched successfully.", response.getBody().getStatusCode() == "200");
		assertTrue("User Id matches", ((List<Task>) response.getBody().getResponseData()).get(0).getUserId()
				.equals("users@taskletAPI.com"));
		logger.info("Get task done");
	}
}