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
import com.lawencon.linovhrcommunity.dto.paymentmethod.DeleteByIdPaymentMethodRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.DeleteMultiplePaymentMethodDtoReq;
import com.lawencon.linovhrcommunity.dto.paymentmethod.DeleteMultiplePaymentMethodDtoRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.GetAllPaymentMethodDtoRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.GetAllPaymentMethodPageDtoRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.GetByIdPaymentMethodDtoRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.InsertPaymentMethodDtoReq;
import com.lawencon.linovhrcommunity.dto.paymentmethod.InsertPaymentMethodDtoRes;
import com.lawencon.linovhrcommunity.dto.paymentmethod.UpdatePaymentMethodDtoReq;
import com.lawencon.linovhrcommunity.dto.paymentmethod.UpdatePaymentMethodDtoRes;
import com.lawencon.linovhrcommunity.dto.threadtype.GetAllThreadTypePageDtoRes;
import com.lawencon.linovhrcommunity.service.PaymentMethodService;

@RestController
@RequestMapping("payment-methods")
public class PaymentMethodController {
	private PaymentMethodService paymentMethodService;

	@Autowired
	public void setPaymentMethodService(PaymentMethodService paymentMethodService) {
		this.paymentMethodService = paymentMethodService;
	}

	@PostMapping
	public ResponseEntity<InsertPaymentMethodDtoRes> insertData(@RequestBody @Valid InsertPaymentMethodDtoReq request)
			throws Exception {
		InsertPaymentMethodDtoRes response = paymentMethodService.insert(request);
		return new ResponseEntity<InsertPaymentMethodDtoRes>(response, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdatePaymentMethodDtoRes> updateData(@RequestBody @Valid UpdatePaymentMethodDtoReq request)
			throws Exception {
		UpdatePaymentMethodDtoRes response = paymentMethodService.update(request);
		return new ResponseEntity<UpdatePaymentMethodDtoRes>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<GetAllPaymentMethodDtoRes> findAll() throws Exception {
		GetAllPaymentMethodDtoRes results = paymentMethodService.findAll();
		return new ResponseEntity<GetAllPaymentMethodDtoRes>(results, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByIdPaymentMethodDtoRes> getById(@PathVariable String id) throws Exception {
		GetByIdPaymentMethodDtoRes data = paymentMethodService.findById(id);
		return new ResponseEntity<GetByIdPaymentMethodDtoRes>(data, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByIdPaymentMethodRes> deleteById(@PathVariable String id) throws Exception {
		DeleteByIdPaymentMethodRes result = paymentMethodService.deleteById(id);
		return new ResponseEntity<DeleteByIdPaymentMethodRes>(result, HttpStatus.OK);
	}

	@GetMapping("page")
	public ResponseEntity<GetAllPaymentMethodPageDtoRes> getAllWithPage(@RequestParam int start, @RequestParam int max)
			throws Exception {
		GetAllPaymentMethodPageDtoRes data = paymentMethodService.getAllWithPage(start, max);
		return new ResponseEntity<GetAllPaymentMethodPageDtoRes>(data, HttpStatus.OK);
	}

	@PostMapping("multiple")
	public ResponseEntity<DeleteMultiplePaymentMethodDtoRes> deleteById(@RequestBody DeleteMultiplePaymentMethodDtoReq dataReq)
			throws Exception {
		DeleteMultiplePaymentMethodDtoRes dataRes = paymentMethodService.deleteMultiple(dataReq);
		return new ResponseEntity<DeleteMultiplePaymentMethodDtoRes>(dataRes, HttpStatus.OK);
	}
}
