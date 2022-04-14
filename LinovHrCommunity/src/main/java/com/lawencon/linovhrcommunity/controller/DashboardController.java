package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.dashboard.GetDashboardDtoRes;
import com.lawencon.linovhrcommunity.service.DashboardService;

@RestController
@RequestMapping("dashboard")
public class DashboardController {
	private DashboardService dashboardService;

	@Autowired
	public void setDashboardService(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}
	
	@GetMapping()
	public ResponseEntity<GetDashboardDtoRes> getDataDashboard() throws Exception {
		GetDashboardDtoRes results = dashboardService.dataDashboard();
		return new ResponseEntity<GetDashboardDtoRes>(results, HttpStatus.OK);
	}
}
