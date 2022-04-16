package com.lawencon.linovhrcommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.linovhrcommunity.dto.eventcoursepayment.GetAllEventCoursePaymentDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.GetReportEventCoursePaymentDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.GetReportEventCoursePaymentDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.InsertEventCoursePaymentDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.UpdateEventCoursePaymentDtoReq;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.UpdateEventCoursePaymentDtoRes;
import com.lawencon.linovhrcommunity.service.EventCoursePaymentService;
import com.lawencon.util.JasperUtil;

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
	public ResponseEntity<InsertEventCoursePaymentDtoRes> pay(@RequestPart(name="content") String content, 
			@RequestPart(required = false) MultipartFile file) throws Exception {
		InsertEventCoursePaymentDtoRes response = eventCoursePaymentService.pay(content, file);
		return new ResponseEntity<InsertEventCoursePaymentDtoRes>(response, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateEventCoursePaymentDtoRes> confirmPayment(@RequestBody @Valid UpdateEventCoursePaymentDtoReq dataReq) throws Exception {
		UpdateEventCoursePaymentDtoRes dataRes = eventCoursePaymentService.confirmPayment(dataReq);
		return new ResponseEntity<UpdateEventCoursePaymentDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<GetAllEventCoursePaymentDtoRes> getAllUnAccepted(@RequestParam Boolean isAccept,@RequestParam int start, @RequestParam int max) throws Exception {
		GetAllEventCoursePaymentDtoRes res = eventCoursePaymentService.getAllUnAccepted(isAccept, start, max);
		return new ResponseEntity<GetAllEventCoursePaymentDtoRes>(res, HttpStatus.OK);
	}
	
	@GetMapping("page/report/admin/payment")
	public ResponseEntity<GetReportEventCoursePaymentDtoRes> getReportPaymentEventCourse(@RequestParam int start, @RequestParam int max) throws Exception {
		GetReportEventCoursePaymentDtoRes res = eventCoursePaymentService.getReportPaymentEventCourse(start, max);
		return new ResponseEntity<GetReportEventCoursePaymentDtoRes>(res, HttpStatus.OK);
	}
	
	@GetMapping("page/report/admin/payment/download")
	public ResponseEntity<?> downloadReportUserJoin(@RequestParam int start, @RequestParam int max) throws Exception {
		GetReportEventCoursePaymentDtoRes data = eventCoursePaymentService.getReportPaymentEventCourse(start, max);
		List<GetReportEventCoursePaymentDtoDataRes> dataRes = data.getData();
		Map<String, Object> map = new HashMap<>();
		map.put("TotalPrice", data.getTotalPrice());
		
		byte[] out = JasperUtil.responseToByteArray(dataRes, "ReportEventCoursePayment", map);
		String fileName = "List_user_join_to_event_course.pdf";
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+fileName+"\"")
				.body(out);
	}
}







