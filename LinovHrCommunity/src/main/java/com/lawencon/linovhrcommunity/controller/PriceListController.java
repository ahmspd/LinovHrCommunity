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
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.pricelist.DeleteByIdPriceListDtoRes;
import com.lawencon.linovhrcommunity.dto.pricelist.GetAllPriceListDtoRes;
import com.lawencon.linovhrcommunity.dto.pricelist.GetByIdPriceListDtoRes;
import com.lawencon.linovhrcommunity.dto.pricelist.InsertPriceListDtoReq;
import com.lawencon.linovhrcommunity.dto.pricelist.InsertPriceListDtoRes;
import com.lawencon.linovhrcommunity.dto.pricelist.UpdatePriceListDtoReq;
import com.lawencon.linovhrcommunity.dto.pricelist.UpdatePriceListDtoRes;
import com.lawencon.linovhrcommunity.service.PriceListService;

@RestController
@RequestMapping("price-list")
public class PriceListController {
	
	private PriceListService priceListService;

	@Autowired
	public void setPriceListService(PriceListService priceListService) {
		this.priceListService = priceListService;
	}
	
	@PostMapping
	public ResponseEntity<InsertPriceListDtoRes> insert(@RequestBody @Valid InsertPriceListDtoReq dataReq) throws Exception {
		InsertPriceListDtoRes dataRes = priceListService.insert(dataReq);
		return new ResponseEntity<InsertPriceListDtoRes>(dataRes, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<GetAllPriceListDtoRes> getAll() throws Exception {
		GetAllPriceListDtoRes res = priceListService.findAll();
		
		return new ResponseEntity<GetAllPriceListDtoRes>(res, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdPriceListDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByIdPriceListDtoRes dataRes = priceListService.findById(id);
		return new ResponseEntity<GetByIdPriceListDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<UpdatePriceListDtoRes> update(@RequestBody @Valid UpdatePriceListDtoReq dataReq) throws Exception {
		UpdatePriceListDtoRes dataRes = priceListService.update(dataReq);
		return new ResponseEntity<UpdatePriceListDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByIdPriceListDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByIdPriceListDtoRes dataRes = priceListService.deleteById(id);
		return new ResponseEntity<DeleteByIdPriceListDtoRes>(dataRes, HttpStatus.OK);
	}
}
