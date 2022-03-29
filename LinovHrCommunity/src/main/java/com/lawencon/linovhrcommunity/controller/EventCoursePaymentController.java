package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.linovhrcommunity.dto.eventcoursepayment.InsertEventCoursePaymentDtoRes;
import com.lawencon.linovhrcommunity.service.EventCoursePaymentService;

@RestController
@RequestMapping("event-course-payment")
public class EventCoursePaymentController {
	private EventCoursePaymentService eventCoursePaymentService;

	@Autowired
	public void setEventCoursePaymentService(EventCoursePaymentService eventCoursePaymentService) {
		this.eventCoursePaymentService = eventCoursePaymentService;
	}

	@PostMapping(consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE
	})
	public ResponseEntity<InsertEventCoursePaymentDtoRes> insertData(@RequestPart(name="content") String content, 
			@RequestPart(required = false) MultipartFile file) throws Exception {
		InsertEventCoursePaymentDtoRes response = eventCoursePaymentService.insert(content, file);
		return new ResponseEntity<InsertEventCoursePaymentDtoRes>(response, HttpStatus.CREATED);
	}

}







