package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoReq;
import com.lawencon.linovhrcommunity.dto.user.InsertUserDtoRes;
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
		return new ResponseEntity<InsertUserDtoRes>(dataUser, HttpStatus.OK);
	}
	
//	@GetMapping
//	public ResponseEntity<?> report()
}
