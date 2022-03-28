package com.lawencon.linovhrcommunity.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.linovhrcommunity.dto.industry.DeleteMultipleIndustryDtoReq;
import com.lawencon.linovhrcommunity.dto.industry.DeleteMultipleIndustryDtoRes;
import com.lawencon.linovhrcommunity.dto.position.DeleteByIdPositionDtoRes;
import com.lawencon.linovhrcommunity.dto.position.DeleteMultiplePositionDtoReq;
import com.lawencon.linovhrcommunity.dto.position.DeleteMultiplePositionDtoRes;
import com.lawencon.linovhrcommunity.dto.position.GetAllPositionDtoRes;
import com.lawencon.linovhrcommunity.dto.position.GetAllPositionPageDtoRes;
import com.lawencon.linovhrcommunity.dto.position.GetByIdPositionDtoRes;
import com.lawencon.linovhrcommunity.dto.position.InsertPositionDtoReq;
import com.lawencon.linovhrcommunity.dto.position.InsertPositionDtoRes;
import com.lawencon.linovhrcommunity.dto.position.UpdatePositionDtoReq;
import com.lawencon.linovhrcommunity.dto.position.UpdatePositionDtoRes;
import com.lawencon.linovhrcommunity.dto.threadtype.GetAllThreadTypePageDtoRes;
import com.lawencon.linovhrcommunity.service.PositionService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@RestController
@RequestMapping("positions")
public class PositionController {
	
	private PositionService positionService;

	@Autowired
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}
	
	@PostMapping
	public ResponseEntity<InsertPositionDtoRes> insert(@RequestBody @Valid InsertPositionDtoReq dataReq) throws Exception {
		InsertPositionDtoRes dataRes = positionService.insert(dataReq);
		return new ResponseEntity<InsertPositionDtoRes>(dataRes, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<GetAllPositionDtoRes> getAll() throws Exception {
		GetAllPositionDtoRes res = positionService.findAll();
		
		return new ResponseEntity<GetAllPositionDtoRes>(res, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdPositionDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByIdPositionDtoRes dataRes = positionService.findById(id);
		return new ResponseEntity<GetByIdPositionDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<UpdatePositionDtoRes> update(@RequestBody @Valid UpdatePositionDtoReq dataReq) throws Exception {
		UpdatePositionDtoRes dataRes = positionService.update(dataReq);
		return new ResponseEntity<UpdatePositionDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByIdPositionDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByIdPositionDtoRes dataRes = positionService.deleteById(id);
		return new ResponseEntity<DeleteByIdPositionDtoRes>(dataRes, HttpStatus.OK);
	}
	
	@GetMapping("/report")
	public ResponseEntity<byte[]> getReport() throws Exception {
		GetAllPositionDtoRes res = positionService.findAll();
		InputStream stream = this.getClass().getResourceAsStream("/coba.jrxml");
		JasperReport report = JasperCompileManager.compileReport(stream);
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(res.getData());
		Map<String, Object> parameters = new HashMap<>();
        parameters.put("tableName", "Position");
        JasperPrint print = JasperFillManager.fillReport(report, parameters, source);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "report.pdf");
		return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(print), headers, HttpStatus.OK);
	}
	
	@GetMapping("page")
	public ResponseEntity<GetAllPositionPageDtoRes> getAllWithPage(@RequestParam int start, @RequestParam int max)
			throws Exception {
		GetAllPositionPageDtoRes data = positionService.getAllWithPage(start, max);
		return new ResponseEntity<GetAllPositionPageDtoRes>(data, HttpStatus.OK);
	}
	
	@DeleteMapping("multiple")
	public ResponseEntity<DeleteMultiplePositionDtoRes> deleteById(@RequestBody DeleteMultiplePositionDtoReq dataReq) throws Exception {
		DeleteMultiplePositionDtoRes dataRes = positionService.deleteMultiple(dataReq);
		return new ResponseEntity<DeleteMultiplePositionDtoRes>(dataRes, HttpStatus.OK);
	}
}
