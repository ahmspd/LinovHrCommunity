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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.bookmark.DeleteBookmarkDtoRes;
import com.lawencon.linovhrcommunity.dto.bookmark.GetBookmarkDtoRes;
import com.lawencon.linovhrcommunity.dto.bookmark.GetBookmarkThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.bookmark.InsertBookmarkDtoReq;
import com.lawencon.linovhrcommunity.dto.bookmark.InsertBookmarkDtoRes;
import com.lawencon.linovhrcommunity.dto.threadtype.GetAllThreadPageDtoRes;
import com.lawencon.linovhrcommunity.service.BookmarkService;

@RestController
@RequestMapping("bookmarks")
public class BookmarkController {
	private BookmarkService bookmarkService;

	@Autowired
	public void setBookmarkService(BookmarkService bookmarkService) {
		this.bookmarkService = bookmarkService;
	}
	
	@PostMapping
	public ResponseEntity<InsertBookmarkDtoRes> insertData(@RequestBody InsertBookmarkDtoReq data) throws Exception {
		InsertBookmarkDtoRes response = bookmarkService.insert(data);
		return new ResponseEntity<InsertBookmarkDtoRes>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("user")
	public ResponseEntity<GetAllThreadPageDtoRes> getById(@RequestParam int start, @RequestParam int max) throws Exception {
		GetAllThreadPageDtoRes dataRes = bookmarkService.getByUser(start, max);
		return new ResponseEntity<GetAllThreadPageDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteBookmarkDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteBookmarkDtoRes dataRes = bookmarkService.delete(id);
		return new ResponseEntity<DeleteBookmarkDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@GetMapping("{idUser}/{idThread}")
	public ResponseEntity<GetBookmarkThreadDtoRes> getBookmarkThread(@PathVariable("idUser") String idUser,@PathVariable("idThread") String idThread) throws Exception {
		GetBookmarkThreadDtoRes dataRes = bookmarkService.getBookmarkThread(idUser, idThread);
		return new ResponseEntity<GetBookmarkThreadDtoRes>(dataRes, HttpStatus.OK);
	}
}
