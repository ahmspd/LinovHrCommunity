package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.order.GetByIdOrderDtoRes;
import com.lawencon.linovhrcommunity.service.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {
	private OrderService orderService;

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByIdOrderDtoRes> getById(@PathVariable String id) throws Exception {
		GetByIdOrderDtoRes data = orderService.findById(id);
		return new ResponseEntity<GetByIdOrderDtoRes>(data, HttpStatus.OK);
	}

}
