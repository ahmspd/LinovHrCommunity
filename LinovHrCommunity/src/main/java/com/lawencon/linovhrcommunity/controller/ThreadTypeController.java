package com.lawencon.linovhrcommunity.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.threadtype.DeleteByIdThreadTypeRes;
import com.lawencon.linovhrcommunity.dto.threadtype.DeleteMultipleThreadTypeDtoReq;
import com.lawencon.linovhrcommunity.dto.threadtype.DeleteMultipleThreadTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.threadtype.GetAllThreadTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.threadtype.GetAllThreadTypePageDtoRes;
import com.lawencon.linovhrcommunity.dto.threadtype.GetByIdThreadTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.threadtype.InsertThreadTypeDtoReq;
import com.lawencon.linovhrcommunity.dto.threadtype.InsertThreadTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.threadtype.UpdateThreadTypeDtoReq;
import com.lawencon.linovhrcommunity.dto.threadtype.UpdateThreadTypeDtoRes;
import com.lawencon.linovhrcommunity.service.ThreadTypeService;

@RestController
@RequestMapping("thread-types")
public class ThreadTypeController {
	private ThreadTypeService threadTypeService;

	@Autowired
	public void setThreadTypeService(ThreadTypeService threadTypeService) {
		this.threadTypeService = threadTypeService;
	}

	@PostMapping
	public ResponseEntity<InsertThreadTypeDtoRes> insertData(@RequestBody @Valid InsertThreadTypeDtoReq request)
			throws Exception {
		InsertThreadTypeDtoRes response = threadTypeService.insert(request);
		return new ResponseEntity<InsertThreadTypeDtoRes>(response, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateThreadTypeDtoRes> updateData(@RequestBody @Valid UpdateThreadTypeDtoReq request)
			throws Exception {
		UpdateThreadTypeDtoRes response = threadTypeService.update(request);
		return new ResponseEntity<UpdateThreadTypeDtoRes>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<GetAllThreadTypeDtoRes> findAll() throws Exception {
		GetAllThreadTypeDtoRes results = threadTypeService.findAll();
		return new ResponseEntity<GetAllThreadTypeDtoRes>(results, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByIdThreadTypeDtoRes> getById(@PathVariable String id) throws Exception {
		GetByIdThreadTypeDtoRes data = threadTypeService.findById(id);
		return new ResponseEntity<GetByIdThreadTypeDtoRes>(data, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByIdThreadTypeRes> deleteById(@PathVariable String id) throws Exception {
		DeleteByIdThreadTypeRes result = threadTypeService.deleteById(id);
		return new ResponseEntity<DeleteByIdThreadTypeRes>(result, HttpStatus.OK);
	}

	@GetMapping("page")
	public ResponseEntity<GetAllThreadTypePageDtoRes> getAllWithPage(@RequestParam int start, @RequestParam int max)
			throws Exception {
		GetAllThreadTypePageDtoRes data = threadTypeService.getAllWithPage(start, max);
		return new ResponseEntity<GetAllThreadTypePageDtoRes>(data, HttpStatus.OK);
	}
	
	@PostMapping("multiple")
	public ResponseEntity<DeleteMultipleThreadTypeDtoRes> deleteById(@RequestBody DeleteMultipleThreadTypeDtoReq dataReq) throws Exception {
		DeleteMultipleThreadTypeDtoRes dataRes = threadTypeService.deleteMultiple(dataReq);
		return new ResponseEntity<DeleteMultipleThreadTypeDtoRes>(dataRes, HttpStatus.OK);
	}
}
