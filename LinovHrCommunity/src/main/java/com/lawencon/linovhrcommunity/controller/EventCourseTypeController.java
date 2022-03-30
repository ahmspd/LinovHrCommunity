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

import com.lawencon.linovhrcommunity.dto.eventcoursetype.DeleteByIdEventCourseTypeRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.GetAllEventCourseTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.GetAllEventCourseTypePageDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.GetByIdEventCourseTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.InsertEventCourseTypeDtoReq;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.InsertEventCourseTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.UpdateEventCourseTypeDtoReq;
import com.lawencon.linovhrcommunity.dto.eventcoursetype.UpdateEventCourseTypeDtoRes;
import com.lawencon.linovhrcommunity.service.EventCourseTypeService;

@RestController
@RequestMapping("event-course-types")
public class EventCourseTypeController {
	private EventCourseTypeService eventCourseTypeService;

	@Autowired
	public void setEventCourseTypeService(EventCourseTypeService eventCourseTypeService) {
		this.eventCourseTypeService = eventCourseTypeService;
	}

	@PostMapping
	public ResponseEntity<InsertEventCourseTypeDtoRes> insertData(@RequestBody @Valid InsertEventCourseTypeDtoReq request) throws Exception {
		InsertEventCourseTypeDtoRes response = eventCourseTypeService.insert(request);
		return new ResponseEntity<InsertEventCourseTypeDtoRes>(response, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateEventCourseTypeDtoRes> updateData(@RequestBody @Valid UpdateEventCourseTypeDtoReq request) throws Exception {
		UpdateEventCourseTypeDtoRes response = eventCourseTypeService.update(request);
		return new ResponseEntity<UpdateEventCourseTypeDtoRes>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<GetAllEventCourseTypeDtoRes> findAll() throws Exception {
		GetAllEventCourseTypeDtoRes results = eventCourseTypeService.findAll();
		return new ResponseEntity<GetAllEventCourseTypeDtoRes>(results, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByIdEventCourseTypeDtoRes> getById(@PathVariable String id) throws Exception {
		GetByIdEventCourseTypeDtoRes data = eventCourseTypeService.findById(id);
		return new ResponseEntity<GetByIdEventCourseTypeDtoRes>(data, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByIdEventCourseTypeRes> deleteById(@PathVariable String id) throws Exception {
		DeleteByIdEventCourseTypeRes result = eventCourseTypeService.deleteById(id);
		return new ResponseEntity<DeleteByIdEventCourseTypeRes>(result, HttpStatus.OK);
	}

	@GetMapping("page")
	public ResponseEntity<GetAllEventCourseTypePageDtoRes> getAllWithPage(@RequestParam int start, @RequestParam int max)
			throws Exception {
		GetAllEventCourseTypePageDtoRes data = eventCourseTypeService.getAllWithPage(start, max);
		return new ResponseEntity<GetAllEventCourseTypePageDtoRes>(data, HttpStatus.OK);
	}
}
