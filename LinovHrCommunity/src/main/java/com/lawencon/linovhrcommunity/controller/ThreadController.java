package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.linovhrcommunity.dto.thread.GetThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadPollingDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoRes;
import com.lawencon.linovhrcommunity.service.ThreadService;

@RestController
@RequestMapping("threads")
public class ThreadController {
	private ThreadService threadService;

	@Autowired
	public void setThreadService(ThreadService threadService) {
		this.threadService = threadService;
	}
	
	@PostMapping(consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE
	})
	public ResponseEntity<InsertThreadDtoRes> insertData(@RequestPart(name="content") String content, 
			@RequestPart(required = false) MultipartFile file) throws Exception {
		InsertThreadDtoRes response = threadService.insert(content, file);
		return new ResponseEntity<InsertThreadDtoRes>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<GetThreadDtoRes> getAllThread() throws Exception {
		GetThreadDtoRes response = threadService.getAllThread();
		return new ResponseEntity<GetThreadDtoRes>(response, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetThreadDtoRes> getThreadByUser(@PathVariable("id") String id) throws Exception {
		GetThreadDtoRes response = threadService.getThreadByUser(id);
		return new ResponseEntity<GetThreadDtoRes>(response, HttpStatus.OK);
	}
	
	@GetMapping("premium/{isPremium}")
	public ResponseEntity<GetThreadDtoRes> getThreadPremium(@PathVariable("isPremium") Boolean isPremium) throws Exception {
		GetThreadDtoRes response = threadService.getThreadPremium(isPremium);
		return new ResponseEntity<GetThreadDtoRes>(response, HttpStatus.OK);
	}

	@GetMapping("type/{id}")
	public ResponseEntity<GetThreadDtoRes> getThreadByType(@PathVariable("id") String id) throws Exception {
		GetThreadDtoRes response = threadService.getThreadByType(id);
		return new ResponseEntity<GetThreadDtoRes>(response, HttpStatus.OK);
	}
	@GetMapping("polling")
	public ResponseEntity<GetThreadPollingDtoRes> getThreadPolling() throws Exception {
		GetThreadPollingDtoRes response = threadService.getAllThreadPolling();
		return new ResponseEntity<GetThreadPollingDtoRes>(response, HttpStatus.OK);
	}
	@GetMapping("polling/{id}")
	public ResponseEntity<GetThreadPollingDtoRes> getThreadPollingByUser(@PathVariable("id") String id) throws Exception {
		GetThreadPollingDtoRes response = threadService.getThreadPollingByUser(id);
		return new ResponseEntity<GetThreadPollingDtoRes>(response, HttpStatus.OK);
	}
}
