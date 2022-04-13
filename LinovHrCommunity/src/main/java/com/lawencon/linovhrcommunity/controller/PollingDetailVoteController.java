package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.pollingdetailvote.GetCountPollingVoteDtoRes;
import com.lawencon.linovhrcommunity.dto.pollingdetailvote.GetPollingDtoRes;
import com.lawencon.linovhrcommunity.dto.pollingdetailvote.InsertPollingVoteDtoReq;
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
	
	@PostMapping()
	public ResponseEntity<InsertPollingVoteDtoRes> insertData(@RequestBody InsertPollingVoteDtoReq data) throws Exception {
		InsertPollingVoteDtoRes response = pollingDetailVoteService.insert(data);
		return new ResponseEntity<InsertPollingVoteDtoRes>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("total/{id}")
	public ResponseEntity<GetCountPollingVoteDtoRes> getPersentase(@PathVariable String id) throws Exception {
		GetCountPollingVoteDtoRes response = pollingDetailVoteService.getVotePolling(id);
		return new ResponseEntity<GetCountPollingVoteDtoRes>(response, HttpStatus.OK);
	}
	
	@GetMapping("{idUser}/{idPollingDetail}")
	public ResponseEntity<GetPollingDtoRes> getBookmarkThread(@PathVariable("idUser") String idUser,@PathVariable("idPollingDetail") String idPollingDetail) throws Exception {
		GetPollingDtoRes dataRes = pollingDetailVoteService.getVote(idUser, idPollingDetail);
		return new ResponseEntity<GetPollingDtoRes>(dataRes, HttpStatus.OK);
	}
}
