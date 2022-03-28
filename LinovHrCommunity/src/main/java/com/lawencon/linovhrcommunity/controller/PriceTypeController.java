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

import com.lawencon.linovhrcommunity.dto.industry.DeleteMultipleIndustryDtoReq;
import com.lawencon.linovhrcommunity.dto.industry.DeleteMultipleIndustryDtoRes;
import com.lawencon.linovhrcommunity.dto.pricetype.DeleteByIdPriceTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.pricetype.DeleteMultiplePriceTypeDtoReq;
import com.lawencon.linovhrcommunity.dto.pricetype.DeleteMultiplePriceTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.pricetype.GetAllPriceTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.pricetype.GetAllPriceTypePageDtoRes;
import com.lawencon.linovhrcommunity.dto.pricetype.GetByIdPriceTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.pricetype.InsertPriceTypeDtoReq;
import com.lawencon.linovhrcommunity.dto.pricetype.InsertPriceTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.pricetype.UpdatePriceTypeDtoReq;
import com.lawencon.linovhrcommunity.dto.pricetype.UpdatePriceTypeDtoRes;
import com.lawencon.linovhrcommunity.dto.threadtype.GetAllThreadTypePageDtoRes;
import com.lawencon.linovhrcommunity.service.PriceTypeService;

@RestController
@RequestMapping("price-types")
public class PriceTypeController {

	private PriceTypeService priceTypeService;

	@Autowired
	public void setPriceTypeService(PriceTypeService priceTypeService) {
		this.priceTypeService = priceTypeService;
	}

	@PostMapping
	public ResponseEntity<InsertPriceTypeDtoRes> insert(@RequestBody @Valid InsertPriceTypeDtoReq dataReq)
			throws Exception {
		InsertPriceTypeDtoRes dataRes = priceTypeService.insert(dataReq);
		return new ResponseEntity<InsertPriceTypeDtoRes>(dataRes, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<GetAllPriceTypeDtoRes> getAll() throws Exception {
		GetAllPriceTypeDtoRes res = priceTypeService.findAll();
		return new ResponseEntity<GetAllPriceTypeDtoRes>(res, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByIdPriceTypeDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByIdPriceTypeDtoRes dataRes = priceTypeService.findById(id);
		return new ResponseEntity<GetByIdPriceTypeDtoRes>(dataRes, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<UpdatePriceTypeDtoRes> update(@RequestBody @Valid UpdatePriceTypeDtoReq dataReq)
			throws Exception {
		UpdatePriceTypeDtoRes dataRes = priceTypeService.update(dataReq);
		return new ResponseEntity<UpdatePriceTypeDtoRes>(dataRes, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByIdPriceTypeDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByIdPriceTypeDtoRes dataRes = priceTypeService.deleteById(id);
		return new ResponseEntity<DeleteByIdPriceTypeDtoRes>(dataRes, HttpStatus.OK);
	}

	@GetMapping("page")
	public ResponseEntity<GetAllPriceTypePageDtoRes> getAllWithPage(@RequestParam int start, @RequestParam int max)
			throws Exception {
		GetAllPriceTypePageDtoRes data = priceTypeService.getAllWithPage(start, max);
		return new ResponseEntity<GetAllPriceTypePageDtoRes>(data, HttpStatus.OK);
	}
	
	@DeleteMapping("multiple")
	public ResponseEntity<DeleteMultiplePriceTypeDtoRes> deleteById(@RequestBody DeleteMultiplePriceTypeDtoReq dataReq) throws Exception {
		DeleteMultiplePriceTypeDtoRes dataRes = priceTypeService.deleteMultiple(dataReq);
		return new ResponseEntity<DeleteMultiplePriceTypeDtoRes>(dataRes, HttpStatus.OK);
	}
}
