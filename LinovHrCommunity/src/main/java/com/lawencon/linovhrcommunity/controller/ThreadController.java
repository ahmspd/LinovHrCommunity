package com.lawencon.linovhrcommunity.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.linovhrcommunity.dto.category.GetAllCategoryPageDtoRes;
import com.lawencon.linovhrcommunity.dto.category.UpdateCategoryDtoReq;
import com.lawencon.linovhrcommunity.dto.category.UpdateCategoryDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDetailDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadPollingDetailDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadPollingDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.UpdateArticleDtoReq;
import com.lawencon.linovhrcommunity.dto.thread.UpdateArticleDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.UpdateThreadStatusDtoReq;
import com.lawencon.linovhrcommunity.dto.thread.UpdateThreadStatusDtoRes;
import com.lawencon.linovhrcommunity.dto.threadtype.GetAllThreadPageDtoRes;
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
	
	@GetMapping("detail/{id}")
	public ResponseEntity<GetThreadDetailDtoRes> getThreadDetail(@PathVariable("id") String id) throws Exception {
		GetThreadDetailDtoRes response = threadService.getThreadDetail(id);
		return new ResponseEntity<GetThreadDetailDtoRes>(response, HttpStatus.OK);
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
	
	@GetMapping("article/{id}")
	public ResponseEntity<GetThreadDtoRes> getArticleNotAccept(@PathVariable("id") String id) throws Exception {
		GetThreadDtoRes response = threadService.getArticleNotAccpet(id);
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
	@GetMapping("polling/detail/{id}")
	public ResponseEntity<GetThreadPollingDetailDtoRes> getThreadPollingById(@PathVariable("id") String id) throws Exception {
		GetThreadPollingDetailDtoRes response = threadService.getThreadPollingById(id);
		return new ResponseEntity<GetThreadPollingDetailDtoRes>(response, HttpStatus.OK);
	}
	
	@GetMapping("page")
	public ResponseEntity<GetAllThreadPageDtoRes> getAllArticleWithPage(@RequestParam String idType,@RequestParam int start, @RequestParam int max)
			throws Exception {
		GetAllThreadPageDtoRes data = threadService.getAllArticleWithPage(idType, start, max);
		return new ResponseEntity<GetAllThreadPageDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("active/page")
	public ResponseEntity<GetAllThreadPageDtoRes> getAllArticleActiveWithPage(@RequestParam String idType,@RequestParam int start, @RequestParam int max,@RequestParam Boolean isActive)
			throws Exception {
		GetAllThreadPageDtoRes data = threadService.getAllArticleActiveWithPage(idType, start, max, isActive);
		return new ResponseEntity<GetAllThreadPageDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("thread/page")
	public ResponseEntity<GetAllThreadPageDtoRes> getAllThreadWithPage(@RequestParam int start, @RequestParam int max)
			throws Exception {
		GetAllThreadPageDtoRes data = threadService.getAllThreadWithPage(start, max);
		return new ResponseEntity<GetAllThreadPageDtoRes>(data, HttpStatus.OK);
	}
	
	@PutMapping("status")
	public ResponseEntity<UpdateThreadStatusDtoRes> update(@RequestBody @Valid UpdateThreadStatusDtoReq dataReq) throws Exception {
		UpdateThreadStatusDtoRes dataRes = threadService.updateStatus(dataReq);
		return new ResponseEntity<UpdateThreadStatusDtoRes>(dataRes, HttpStatus.OK);
	}
	@PutMapping("article")
	public ResponseEntity<UpdateArticleDtoRes> update(@RequestBody @Valid UpdateArticleDtoReq dataReq) throws Exception {
		UpdateArticleDtoRes dataRes = threadService.updateArticle(dataReq);
		return new ResponseEntity<UpdateArticleDtoRes>(dataRes, HttpStatus.OK);
	}
}
