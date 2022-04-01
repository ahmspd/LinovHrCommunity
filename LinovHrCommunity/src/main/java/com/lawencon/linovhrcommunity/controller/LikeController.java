package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.like.DeleteLikeDtoRes;
import com.lawencon.linovhrcommunity.dto.like.GetLikeDtoRes;
import com.lawencon.linovhrcommunity.dto.like.GetLikeThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.like.InsertLikeDtoReq;
import com.lawencon.linovhrcommunity.dto.like.InsertLikeDtoRes;
import com.lawencon.linovhrcommunity.service.LikeService;

@RestController
@RequestMapping("likes")
public class LikeController {
	private LikeService likeService;

	@Autowired
	public void setLikeService(LikeService likeService) {
		this.likeService = likeService;
	}
	
	@PostMapping
	public ResponseEntity<InsertLikeDtoRes> insertData(@RequestBody InsertLikeDtoReq data) throws Exception {
		InsertLikeDtoRes response = likeService.insert(data);
		return new ResponseEntity<InsertLikeDtoRes>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<GetLikeDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetLikeDtoRes dataRes = likeService.getByUser(id);
		return new ResponseEntity<GetLikeDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteLikeDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteLikeDtoRes dataRes = likeService.delete(id);
		return new ResponseEntity<DeleteLikeDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@GetMapping("{idUser}/{idThread}")
	public ResponseEntity<GetLikeThreadDtoRes> getLikeThread(@PathVariable("idUser") String idUser,@PathVariable("idThread") String idThread) throws Exception {
		GetLikeThreadDtoRes dataRes = likeService.getBookmarkThread(idUser, idThread);
		return new ResponseEntity<GetLikeThreadDtoRes>(dataRes, HttpStatus.OK);
	}
}
