package com.lawencon.linovhrcommunity.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.lawencon.linovhrcommunity.dto.usermember.GetAllUserMemberDtoRes;
import com.lawencon.linovhrcommunity.dto.usermember.InsertUserMemberDtoReq;
import com.lawencon.linovhrcommunity.dto.usermember.InsertUserMemberDtoRes;
import com.lawencon.linovhrcommunity.dto.usermember.UpdateUserMemberAcceptDtoReq;
import com.lawencon.linovhrcommunity.dto.usermember.UpdateUserMemberAcceptDtoRes;
import com.lawencon.linovhrcommunity.dto.usermember.UpdateUserMemberPaymentDtoRes;
import com.lawencon.linovhrcommunity.service.UserMemberService;

@RestController
@RequestMapping("user-members")
public class UserMemberController {
	private UserMemberService userMemberService;

	@Autowired
	public void setUserMemberService(UserMemberService userMemberService) {
		this.userMemberService = userMemberService;
	}

	@PostMapping
	public ResponseEntity<InsertUserMemberDtoRes> insertData(@RequestBody @Valid InsertUserMemberDtoReq request)
			throws Exception {
		InsertUserMemberDtoRes response = userMemberService.insert(request);
		return new ResponseEntity<InsertUserMemberDtoRes>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<GetAllUserMemberDtoRes> findAll(@RequestParam Boolean isAccept,@RequestParam int start, @RequestParam int max) throws Exception {
		GetAllUserMemberDtoRes results = userMemberService.getAllIsNotAccept(isAccept, start, max);
		return new ResponseEntity<GetAllUserMemberDtoRes>(results, HttpStatus.OK);
	}

	@PutMapping(value = "/file-payment", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<UpdateUserMemberPaymentDtoRes> updatePayment(@RequestPart(name = "content") String content,
			@RequestPart(required = false) MultipartFile file) throws Exception {
		UpdateUserMemberPaymentDtoRes response = userMemberService.updatePayment(content, file);
		return new ResponseEntity<UpdateUserMemberPaymentDtoRes>(response, HttpStatus.OK);
	}

	@PutMapping("/premium")
	public ResponseEntity<UpdateUserMemberAcceptDtoRes> updateAccept(
			@RequestBody @Valid UpdateUserMemberAcceptDtoReq request) throws Exception {
		UpdateUserMemberAcceptDtoRes response = userMemberService.updateAccept(request);
//		userMemberService.scheduleUserMember(request.getIdUserMember());
		return new ResponseEntity<UpdateUserMemberAcceptDtoRes>(response, HttpStatus.OK);
	}
}
