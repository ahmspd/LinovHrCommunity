package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.pollingdetailvote.GetCountPollingVoteDtoRes;
import com.lawencon.linovhrcommunity.dto.pollingdetailvote.InsertPollingVoteDtoRes;
import com.lawencon.linovhrcommunity.service.PollingDetailVoteService;

@RestController
@RequestMapping("polling-detail-votes")
public class PollingDetailVoteController {

	private PollingDetailVoteService pollingDetailVoteService;

	@Autowired
	public void setPollingDetailVoteService(PollingDetailVoteService pollingDetailVoteService) {
		this.pollingDetailVoteService = pollingDetailVoteService;
	}
	
	@PostMapping("{id}")
	public ResponseEntity<InsertPollingVoteDtoRes> insertData(@PathVariable String id) throws Exception {
		InsertPollingVoteDtoRes response = pollingDetailVoteService.insert(id);
		return new ResponseEntity<InsertPollingVoteDtoRes>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("total/{id}")
	public ResponseEntity<GetCountPollingVoteDtoRes> getPersentase(@PathVariable String id) throws Exception {
		GetCountPollingVoteDtoRes response = pollingDetailVoteService.getVotePolling(null);
		return new ResponseEntity<GetCountPollingVoteDtoRes>(response, HttpStatus.OK);
	}
}
