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

import com.lawencon.linovhrcommunity.dto.role.DeleteByIdRoleRes;
import com.lawencon.linovhrcommunity.dto.role.GetAllRoleDtoRes;
import com.lawencon.linovhrcommunity.dto.role.GetAllRolePageDtoRes;
import com.lawencon.linovhrcommunity.dto.role.GetByIdRoleDtoRes;
import com.lawencon.linovhrcommunity.dto.role.InsertRoleDtoReq;
import com.lawencon.linovhrcommunity.dto.role.InsertRoleDtoRes;
import com.lawencon.linovhrcommunity.dto.role.UpdateRoleDtoReq;
import com.lawencon.linovhrcommunity.dto.role.UpdateRoleDtoRes;
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
	public ResponseEntity<InsertRoleDtoRes> insertData(@RequestBody @Valid InsertRoleDtoReq request) throws Exception {
		InsertRoleDtoRes response = roleService.insert(request);
		return new ResponseEntity<InsertRoleDtoRes>(response, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateRoleDtoRes> updateData(@RequestBody @Valid UpdateRoleDtoReq request) throws Exception {
		UpdateRoleDtoRes response = roleService.update(request);
		return new ResponseEntity<UpdateRoleDtoRes>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<GetAllRoleDtoRes> findAll() throws Exception {
		GetAllRoleDtoRes results = roleService.findAll();
		return new ResponseEntity<GetAllRoleDtoRes>(results, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByIdRoleDtoRes> getById(@PathVariable String id) throws Exception {
		GetByIdRoleDtoRes data = roleService.findById(id);
		return new ResponseEntity<GetByIdRoleDtoRes>(data, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByIdRoleRes> deleteById(@PathVariable String id) throws Exception {
		DeleteByIdRoleRes result = roleService.deleteById(id);
		return new ResponseEntity<DeleteByIdRoleRes>(result, HttpStatus.OK);
	}

	@GetMapping("page")
	public ResponseEntity<GetAllRolePageDtoRes> getAllWithPage(@RequestParam int start, @RequestParam int max) throws Exception {
		GetAllRolePageDtoRes data = roleService.getAllWithPage(start, max);
		return new ResponseEntity<GetAllRolePageDtoRes>(data, HttpStatus.OK);
	}
}
