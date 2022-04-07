package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.city.GetAllCityByProvinceDtoRes;
import com.lawencon.linovhrcommunity.service.CityService;

@RestController
@RequestMapping("cities")
public class CityController {
	private CityService cityService;

	@Autowired
	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}
	
	@GetMapping("code")
	public ResponseEntity<GetAllCityByProvinceDtoRes> findAllByProvince(@PathVariable String code) throws Exception {
		GetAllCityByProvinceDtoRes results = cityService.getAllCityByProvince(code);
		return new ResponseEntity<GetAllCityByProvinceDtoRes>(results, HttpStatus.OK);
	}
}
