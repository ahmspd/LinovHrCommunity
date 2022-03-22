package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.role.InsertRoleDtoReq;
import com.lawencon.linovhrcommunity.service.RoleService;

@RestController
@RequestMapping("roles")
public class RoleController {
	private RoleService roleService;

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody InsertRoleDtoReq data) throws Exception{
		System.out.println(data.getCode());
		roleService.insert(data);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
