package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.threaddetail.InsertThreadDetailDtoReq;
import com.lawencon.linovhrcommunity.dto.threaddetail.InsertThreadDetailDtoRes;
import com.lawencon.linovhrcommunity.service.ThreadDetailService;

@RestController
@RequestMapping("thread-details")
public class ThreadDetailController {
	private ThreadDetailService threadDetailService;

	@Autowired
	public void setThreadDetailService(ThreadDetailService threadDetailService) {
		this.threadDetailService = threadDetailService;
	}
	
	@PostMapping
	public ResponseEntity<InsertThreadDetailDtoRes> insertData(@RequestBody InsertThreadDetailDtoReq data) throws Exception {
		InsertThreadDetailDtoRes response = threadDetailService.insert(data);
		return new ResponseEntity<InsertThreadDetailDtoRes>(response, HttpStatus.CREATED);
	}
}