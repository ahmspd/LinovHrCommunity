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

import com.lawencon.linovhrcommunity.dto.industry.DeleteByIdIndustryDtoRes;
import com.lawencon.linovhrcommunity.dto.industry.DeleteMultipleIndustryDtoReq;
import com.lawencon.linovhrcommunity.dto.industry.DeleteMultipleIndustryDtoRes;
import com.lawencon.linovhrcommunity.dto.industry.GetAllIndustryDtoRes;
import com.lawencon.linovhrcommunity.dto.industry.GetAllIndustryPageDtoRes;
import com.lawencon.linovhrcommunity.dto.industry.GetByIdIndustryDtoRes;
import com.lawencon.linovhrcommunity.dto.industry.InsertIndustryDtoReq;
import com.lawencon.linovhrcommunity.dto.industry.InsertIndustryDtoRes;
import com.lawencon.linovhrcommunity.dto.industry.UpdateIndustryDtoReq;
import com.lawencon.linovhrcommunity.dto.industry.UpdateIndustryDtoRes;
import com.lawencon.linovhrcommunity.dto.threadtype.GetAllThreadTypePageDtoRes;
import com.lawencon.linovhrcommunity.service.IndustryService;

@RestController
@RequestMapping("industries")
public class IndustryController {
	
	private IndustryService industryService;

	@Autowired
	public void setIndustryService(IndustryService industryService) {
		this.industryService = industryService;
	}
	
	@PostMapping
	public ResponseEntity<InsertIndustryDtoRes> insert(@RequestBody @Valid InsertIndustryDtoReq dataReq) throws Exception {
		InsertIndustryDtoRes dataRes = industryService.insert(dataReq);
		return new ResponseEntity<InsertIndustryDtoRes>(dataRes, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<GetAllIndustryDtoRes> getAll() throws Exception {
		GetAllIndustryDtoRes res = industryService.findAll();
		
		return new ResponseEntity<GetAllIndustryDtoRes>(res, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdIndustryDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByIdIndustryDtoRes dataRes = industryService.findById(id);
		return new ResponseEntity<GetByIdIndustryDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<UpdateIndustryDtoRes> update(@RequestBody @Valid UpdateIndustryDtoReq dataReq) throws Exception {
		UpdateIndustryDtoRes dataRes = industryService.update(dataReq);
		return new ResponseEntity<UpdateIndustryDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByIdIndustryDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByIdIndustryDtoRes dataRes = industryService.deleteById(id);
		return new ResponseEntity<DeleteByIdIndustryDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@GetMapping("page")
	public ResponseEntity<GetAllIndustryPageDtoRes> getAllWithPage(@RequestParam int start, @RequestParam int max)
			throws Exception {
		GetAllIndustryPageDtoRes data = industryService.getAllWithPage(start, max);
		return new ResponseEntity<GetAllIndustryPageDtoRes>(data, HttpStatus.OK);
	}
	
	@DeleteMapping("multiple")
	public ResponseEntity<DeleteMultipleIndustryDtoRes> deleteById(@RequestBody DeleteMultipleIndustryDtoReq dataReq) throws Exception {
		DeleteMultipleIndustryDtoRes dataRes = industryService.deleteMultiple(dataReq);
		return new ResponseEntity<DeleteMultipleIndustryDtoRes>(dataRes, HttpStatus.OK);
	}
}
