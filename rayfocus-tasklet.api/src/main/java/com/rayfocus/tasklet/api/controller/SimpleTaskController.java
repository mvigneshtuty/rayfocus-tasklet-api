package com.rayfocus.tasklet.api.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rayfocus.tasklet.api.model.SimpleTask;

@RestController
public class SimpleTaskController {

	private final AtomicLong idCounter = new AtomicLong();

	@RequestMapping("/simpletask")
	public SimpleTask simpleTask(@RequestParam(value = "name", defaultValue = "HelloWorld") String taskName) {
		return new SimpleTask(idCounter.incrementAndGet(), "Simple " + taskName + " Task!");
	}
}
