package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.province.GetAllProvinceDtoRes;
import com.lawencon.linovhrcommunity.service.ProvinceService;

@RestController
@RequestMapping("provinces")
public class ProvinceController {
	private ProvinceService provinceService;

	@Autowired
	public void setProvinceService(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllProvinceDtoRes> findAll() throws Exception {
		GetAllProvinceDtoRes results = provinceService.getAllProvince();
		return new ResponseEntity<GetAllProvinceDtoRes>(results, HttpStatus.OK);
	}
}
