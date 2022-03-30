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

import com.lawencon.linovhrcommunity.dto.category.DeleteByIdCategoryDtoDataRes;
import com.lawencon.linovhrcommunity.dto.category.DeleteMultipleCategoryDtoReq;
import com.lawencon.linovhrcommunity.dto.category.DeleteMultipleCategoryDtoRes;
import com.lawencon.linovhrcommunity.dto.category.GetAllCategoryDtoRes;
import com.lawencon.linovhrcommunity.dto.category.GetAllCategoryPageDtoRes;
import com.lawencon.linovhrcommunity.dto.category.GetByIdCategoryDtoRes;
import com.lawencon.linovhrcommunity.dto.category.InsertCategoryDtoReq;
import com.lawencon.linovhrcommunity.dto.category.InsertCategoryDtoRes;
import com.lawencon.linovhrcommunity.dto.category.UpdateCategoryDtoReq;
import com.lawencon.linovhrcommunity.dto.category.UpdateCategoryDtoRes;
import com.lawencon.linovhrcommunity.service.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController {
	
	private CategoryService categoryService;
	
	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping
	public ResponseEntity<InsertCategoryDtoRes> insert(@RequestBody @Valid InsertCategoryDtoReq dataReq) throws Exception {
		InsertCategoryDtoRes dataRes = categoryService.insert(dataReq);
		return new ResponseEntity<InsertCategoryDtoRes>(dataRes, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<GetAllCategoryDtoRes> getAll() throws Exception {
		GetAllCategoryDtoRes res = categoryService.findAll();
		return new ResponseEntity<GetAllCategoryDtoRes>(res, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdCategoryDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByIdCategoryDtoRes dataRes = categoryService.findById(id);
		return new ResponseEntity<GetByIdCategoryDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<UpdateCategoryDtoRes> update(@RequestBody @Valid UpdateCategoryDtoReq dataReq) throws Exception {
		UpdateCategoryDtoRes dataRes = categoryService.update(dataReq);
		return new ResponseEntity<UpdateCategoryDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByIdCategoryDtoDataRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByIdCategoryDtoDataRes dataRes = categoryService.deleteById(id);
		return new ResponseEntity<DeleteByIdCategoryDtoDataRes>(dataRes, HttpStatus.OK);
	}
	
	@GetMapping("page")
	public ResponseEntity<GetAllCategoryPageDtoRes> getAllWithPage(@RequestParam int start, @RequestParam int max)
			throws Exception {
		GetAllCategoryPageDtoRes data = categoryService.getAllWithPage(start, max);
		return new ResponseEntity<GetAllCategoryPageDtoRes>(data, HttpStatus.OK);
	}
	
	@PostMapping("multiple")
	public ResponseEntity<DeleteMultipleCategoryDtoRes> deleteById(@RequestBody DeleteMultipleCategoryDtoReq dataReq) throws Exception {
		DeleteMultipleCategoryDtoRes dataRes = categoryService.deleteMultiple(dataReq);
		return new ResponseEntity<DeleteMultipleCategoryDtoRes>(dataRes, HttpStatus.OK);
	}
}
