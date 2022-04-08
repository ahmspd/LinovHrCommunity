package com.lawencon.linovhrcommunity.controller;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.linovhrcommunity.dto.user.GetUserDtoRes;
import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoReq;
import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoRes;
import com.lawencon.linovhrcommunity.dto.user.RegistrationCodeDtoReq;
import com.lawencon.linovhrcommunity.dto.user.RegistrationCodeDtoRes;
import com.lawencon.linovhrcommunity.dto.user.UpdatePasswordDtoReq;
import com.lawencon.linovhrcommunity.dto.user.UpdatePasswordDtoRes;
import com.lawencon.linovhrcommunity.dto.user.UpdateUserDtoReq;
import com.lawencon.linovhrcommunity.dto.user.UpdateUserDtoRes;
import com.lawencon.linovhrcommunity.dto.user.UserForgotPasswordDtoReq;
import com.lawencon.linovhrcommunity.dto.user.UserForgotPasswordDtoRes;
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
	
	@PutMapping("registration")
	public ResponseEntity<RegistrationCodeDtoRes> registrationCode(@RequestBody RegistrationCodeDtoReq dataReq) throws Exception {
		RegistrationCodeDtoRes data = userService.insertRegistraionCode(dataReq);
		return new ResponseEntity<RegistrationCodeDtoRes>(data, HttpStatus.OK);
	}
	
	@PutMapping(consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE
	})
	public ResponseEntity<UpdateUserDtoRes> updateUser(@RequestPart(name="content") String content, 
			@RequestPart(required = false) MultipartFile file) throws Exception {
		UpdateUserDtoRes response = userService.updateUser(content, file);
		return new ResponseEntity<UpdateUserDtoRes>(response, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetUserDtoRes> getById(@PathVariable String id) throws Exception {
		GetUserDtoRes data = userService.getByUserId(id);
		return new ResponseEntity<GetUserDtoRes>(data, HttpStatus.OK);
	}
	
	@PutMapping("update")
	public ResponseEntity<UpdateUserDtoRes> updateIsActive(@RequestBody UpdateUserDtoReq dataReq) throws Exception {
		UpdateUserDtoRes data = userService.updateByRegistraionCode(dataReq);
		return new ResponseEntity<UpdateUserDtoRes>(data, HttpStatus.OK);
	}
	
	@PutMapping("password")
	public ResponseEntity<UpdatePasswordDtoRes> updatePassword(@RequestBody UpdatePasswordDtoReq dataReq) throws Exception {
		UpdatePasswordDtoRes data = userService.updatePassword(dataReq);
		return new ResponseEntity<UpdatePasswordDtoRes>(data, HttpStatus.OK);
	}
	
	@PutMapping("forgot-password")
	public ResponseEntity<UserForgotPasswordDtoRes> forgotPassword(@RequestBody UserForgotPasswordDtoReq datReq) throws Exception {
		UserForgotPasswordDtoRes data = userService.forgotPassword(datReq);
		return new ResponseEntity<UserForgotPasswordDtoRes>(data, HttpStatus.OK);
	}
	
}
