package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.testjob.TestServiceJob;

@RestController
@RequestMapping("test-job")
public class TestJobController {
	
	private TestServiceJob testServiceJob;

	@Autowired
	public void setTestServiceJob(TestServiceJob testServiceJob) {
		this.testServiceJob = testServiceJob;
	}
	
	@PostMapping
	public void run() {
		testServiceJob.runJob();
	}
}
