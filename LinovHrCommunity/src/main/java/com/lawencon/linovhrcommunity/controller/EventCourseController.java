package com.lawencon.linovhrcommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.linovhrcommunity.dto.eventcourse.ConfirmPayJoinEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetAllEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetByIdEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetOrderEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetProfileJoinEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetReportEventCourseById;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetReportEventCourseByIdRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.InsertEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.JoinEventCourseDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.PayJoinEventCourseDtoRes;
import com.lawencon.linovhrcommunity.service.EventCourseService;
import com.lawencon.util.JasperUtil;

@RestController
@RequestMapping("event-course")
public class EventCourseController {
	private EventCourseService eventCourseService;

	@Autowired
	public void setEventCourseService(EventCourseService eventCourseService) {
		this.eventCourseService = eventCourseService;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<InsertEventCourseDtoRes> insertData(@RequestPart(name = "content") String content,
			@RequestPart(required = false) MultipartFile file) throws Exception {
		InsertEventCourseDtoRes response = eventCourseService.insert(content, file);
		return new ResponseEntity<InsertEventCourseDtoRes>(response, HttpStatus.CREATED);
	}

	@GetMapping("{type}")
	public ResponseEntity<GetAllEventCourseDtoRes> getByCreatedBy(@PathVariable("type") String type) throws Exception {
		GetAllEventCourseDtoRes dataRes = eventCourseService.getByCreatedBy(type);
		return new ResponseEntity<GetAllEventCourseDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<GetByIdEventCourseDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByIdEventCourseDtoRes dataRes = eventCourseService.getById(id);
		return new ResponseEntity<GetByIdEventCourseDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@GetMapping("joined")
	public ResponseEntity<GetAllEventCourseDtoRes> getJoinedEventCourse() throws Exception {
		GetAllEventCourseDtoRes dataRes = eventCourseService.getJoinedEventCourse();
		return new ResponseEntity<GetAllEventCourseDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@GetMapping("profiles/{id}")
	public ResponseEntity<GetProfileJoinEventCourseDtoRes> getProfileJoinedEventCourse(@PathVariable("id") String id) throws Exception {
		GetProfileJoinEventCourseDtoRes dataRes = eventCourseService.getProfileJoin(id);
		return new ResponseEntity<GetProfileJoinEventCourseDtoRes>(dataRes, HttpStatus.OK);
	}

	@GetMapping("type/{type}/{idUser}")
	public ResponseEntity<GetAllEventCourseDtoRes> getAllActive(@PathVariable("type") String type,@PathVariable("idUser") String idUser) throws Exception {
		GetAllEventCourseDtoRes dataRes = eventCourseService.getAllActive(type,idUser);
		return new ResponseEntity<GetAllEventCourseDtoRes>(dataRes, HttpStatus.OK);
	}

	@GetMapping("detail/{id}")
	public ResponseEntity<GetAllEventCourseDtoRes> getByEventCoursePaymentId(@PathVariable("id") String id)
			throws Exception {
		GetAllEventCourseDtoRes dataRes = eventCourseService.getByEventCoursePaymentId(id);
		return new ResponseEntity<GetAllEventCourseDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@GetMapping("order-list/{id}")
	public ResponseEntity<GetOrderEventCourseDtoRes> getOrderEventCourse(@PathVariable("id") String id)
			throws Exception {
		GetOrderEventCourseDtoRes dataRes = eventCourseService.getOrderEventCourse(id);
		return new ResponseEntity<GetOrderEventCourseDtoRes>(dataRes, HttpStatus.OK);
	}

	@PostMapping("join/{id}")
	public ResponseEntity<JoinEventCourseDtoRes> joinEventCourse(@PathVariable("id") String id) throws Exception {
		JoinEventCourseDtoRes dataRes = eventCourseService.joinEventCourse(id);
		return new ResponseEntity<JoinEventCourseDtoRes>(dataRes, HttpStatus.CREATED);
	}

	@PutMapping("pay")
	public ResponseEntity<PayJoinEventCourseDtoRes> payJoin(@RequestPart(name = "content") String content,
			@RequestPart(required = true) MultipartFile file) throws Exception {
		PayJoinEventCourseDtoRes response = eventCourseService.payJoin(content, file);
		return new ResponseEntity<PayJoinEventCourseDtoRes>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("confirm/{id}")
	public ResponseEntity<ConfirmPayJoinEventCourseDtoRes> confirmPayJoin(@PathVariable("id") String id) throws Exception {
		ConfirmPayJoinEventCourseDtoRes response = eventCourseService.confirmJoinPayment(id);
		return new ResponseEntity<ConfirmPayJoinEventCourseDtoRes>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("page/report/admin")
	public ResponseEntity<GetReportEventCourseByIdRes> getReportUserJoinByAdmin(@RequestParam int start, @RequestParam int max) throws Exception {
		GetReportEventCourseByIdRes res = eventCourseService.getReportUserJoinByAdmin(start, max);
		return new ResponseEntity<GetReportEventCourseByIdRes>(res, HttpStatus.OK);
	}
	
	@GetMapping("page/report/admin/download")
	public ResponseEntity<?> downloadReportUserJoin(@RequestParam int start, @RequestParam int max) throws Exception {
		GetReportEventCourseByIdRes data = eventCourseService.getReportUserJoinByAdmin(start, max);
		List<GetReportEventCourseById> dataRes = data.getData();
		Map<String, Object> map = new HashMap<>();
		
		byte[] out = JasperUtil.responseToByteArray(dataRes, "ReportEventCourseJoin", map);
		String fileName = "contoh.pdf";
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+fileName+"\"")
				.body(out);
	}
	
	@GetMapping("report/{idEventCourse}")
	public ResponseEntity<?> getReportUserJoinByIdEvent(@PathVariable("idEventCourse") String idEventCourse) throws Exception {
		GetReportEventCourseByIdRes data = eventCourseService.getReportUserJoinByIdEvent(idEventCourse);
		List<GetReportEventCourseById> dataRes = data.getData();
		Map<String, Object> map = new HashMap<>();
		map.put("TotalPrice", data.getTotalPrice());
		map.put("EventCourseName", dataRes.get(0).getTitle());
		
		byte[] out = JasperUtil.responseToByteArray(dataRes, "ReportEventCourseById", map);
		String fileName = "contoh.pdf";
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+fileName+"\"")
				.body(out);
	}
	
	@GetMapping("report/all/{idUser}")
	public ResponseEntity<?> getReportUserJoinByUser(@PathVariable("idUser") String idUser) throws Exception {
		GetReportEventCourseByIdRes data = eventCourseService.getReportUserJoinByUser(idUser);
		List<GetReportEventCourseById> dataRes = data.getData();
		Map<String, Object> map = new HashMap<>();
		map.put("TotalPrice", data.getTotalPrice());
		
		byte[] out = JasperUtil.responseToByteArray(dataRes, "ReportEventCourseByUser", map);
		String fileName = "contoh.pdf";
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\""+fileName+"\"")
				.body(out);
	}

}
