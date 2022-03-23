package com.lawencon.linovhrcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.linovhrcommunity.dto.file.DeleteByIdFileDtoRes;
import com.lawencon.linovhrcommunity.dto.file.GetAllFileDtoRes;
import com.lawencon.linovhrcommunity.dto.file.GetByIdFileDtoRes;
import com.lawencon.linovhrcommunity.dto.file.InsertFileDtoRes;
import com.lawencon.linovhrcommunity.dto.file.UpdateFileDtoRes;
import com.lawencon.linovhrcommunity.service.FileService;


@RestController
@RequestMapping("files")
public class FileController {

    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
    
    @PostMapping
	public ResponseEntity<InsertFileDtoRes> insert(@RequestPart(required = true) MultipartFile file) throws Exception {
		InsertFileDtoRes dataRes = fileService.insert(file);
		return new ResponseEntity<InsertFileDtoRes>(dataRes, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<GetAllFileDtoRes> getAll() throws Exception {
		GetAllFileDtoRes res = fileService.findAll();
		
		return new ResponseEntity<GetAllFileDtoRes>(res, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdFileDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByIdFileDtoRes dataRes = fileService.findById(id);
		return new ResponseEntity<GetByIdFileDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UpdateFileDtoRes> update(@RequestPart(name = "content") String content, @RequestPart(required = true) MultipartFile file) throws Exception {
		UpdateFileDtoRes dataRes = fileService.update(content, file);
		return new ResponseEntity<UpdateFileDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByIdFileDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByIdFileDtoRes dataRes = fileService.deleteById(id);
		return new ResponseEntity<DeleteByIdFileDtoRes>(dataRes, HttpStatus.OK);
	}

    @GetMapping("download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") String id) throws Exception {
        GetByIdFileDtoRes file = fileService.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "file;filename=file." + file.getData().getExtensions());

        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_OCTET_STREAM).body(file.getData().getContents());
    }
}
