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

import com.lawencon.linovhrcommunity.dto.eventcourse.GetAllEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.InsertEventCourseDtoRes;
import com.lawencon.linovhrcommunity.service.EventCourseService;

@RestController
@RequestMapping("event-course")
public class EventCourseController {
	private EventCourseService eventCourseService;

	@Autowired
	public void setEventCourseService(EventCourseService eventCourseService) {
		this.eventCourseService = eventCourseService;
	}

	@PostMapping(consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE
	})
	public ResponseEntity<InsertEventCourseDtoRes> insertData(@RequestPart(name="content") String content, 
			@RequestPart(required = false) MultipartFile file) throws Exception {
		InsertEventCourseDtoRes response = eventCourseService.pay(content, file);
		return new ResponseEntity<InsertEventCourseDtoRes>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("{type}")
	public ResponseEntity<GetAllEventCourseDtoRes> getAllActive(@PathVariable("type") String type) throws Exception {
		GetAllEventCourseDtoRes dataRes = eventCourseService.getAllActive(type);
		return new ResponseEntity<GetAllEventCourseDtoRes>(dataRes, HttpStatus.OK);
	}

}
