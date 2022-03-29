package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoReq;
import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoRes;
import com.lawencon.linovhrcommunity.dto.user.UpdateUserDtoRes;
import com.lawencon.linovhrcommunity.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<InsertUserDtoRes> insertUser(@RequestBody InsertUserDtoReq data) throws Exception {
		InsertUserDtoRes dataUser = userService.insert(data);
		return new ResponseEntity<InsertUserDtoRes>(dataUser, HttpStatus.CREATED);
	}
	
	@PutMapping(consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE
	})
	public ResponseEntity<UpdateUserDtoRes> insertData(@RequestPart(name="content") String content, 
			@RequestPart(required = false) MultipartFile file) throws Exception {
		UpdateUserDtoRes response = userService.updateUser(content, file);
		return new ResponseEntity<UpdateUserDtoRes>(response, HttpStatus.OK);
	}
}
