package com.lawencon.linovhrcommunity.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoReq;
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
	
	@PostMapping
	public ResponseEntity<InsertThreadDtoRes> insertData(@RequestBody @Valid InsertThreadDtoReq request) throws Exception {
		InsertThreadDtoRes response = threadService.insert(request);
		return new ResponseEntity<InsertThreadDtoRes>(response, HttpStatus.CREATED);
	}
}
