package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.linovhrcommunity.dao.FileDao;
import com.lawencon.linovhrcommunity.dto.file.DeleteByIdFileDtoRes;
import com.lawencon.linovhrcommunity.dto.file.GetAllFileDtoDataRes;
import com.lawencon.linovhrcommunity.dto.file.GetAllFileDtoRes;
import com.lawencon.linovhrcommunity.dto.file.GetByIdFileDtoDataRes;
import com.lawencon.linovhrcommunity.dto.file.GetByIdFileDtoRes;
import com.lawencon.linovhrcommunity.dto.file.InsertFileDtoDataRes;
import com.lawencon.linovhrcommunity.dto.file.InsertFileDtoRes;
import com.lawencon.linovhrcommunity.dto.file.UpdateFileDtoDataRes;
import com.lawencon.linovhrcommunity.dto.file.UpdateFileDtoReq;
import com.lawencon.linovhrcommunity.dto.file.UpdateFileDtoRes;
import com.lawencon.linovhrcommunity.model.File;

@Service
public class FileService extends BaseServiceLinovCommunityImpl {

	private FileDao fileDao;
	private String createdById = "1";

	@Autowired
	public FileService(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	public InsertFileDtoRes insert(MultipartFile file) throws Exception {
		File addFile = new File();
		addFile.setExtensions(getExtension(file));
		addFile.setContents(file.getBytes());
		addFile.setCreatedBy(createdById);

		File fileAdded;
		try {
			begin();
			fileAdded = fileDao.save(addFile);
			commit();
			
			InsertFileDtoDataRes data = new InsertFileDtoDataRes();
			data.setId(fileAdded.getId());

			InsertFileDtoRes dataRes = new InsertFileDtoRes();
			dataRes.setData(data);
			dataRes.setMessage("Insert Success");
			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public UpdateFileDtoRes update(String content, MultipartFile file) throws Exception {
		UpdateFileDtoReq dataReq = new ObjectMapper().readValue(content, UpdateFileDtoReq.class);
		
		File updateFile = fileDao.findById(dataReq.getId());
		updateFile.setExtensions(getExtension(file));
		updateFile.setContents(file.getBytes());
		updateFile.setUpdatedBy(createdById);
		updateFile.setVersion(dataReq.getVersion());

		File fileUpdated;
		try {
			begin();
			fileUpdated = fileDao.save(updateFile);
			commit();
			
			UpdateFileDtoDataRes data = new UpdateFileDtoDataRes();
			data.setVersion(fileUpdated.getVersion());

			UpdateFileDtoRes dataRes = new UpdateFileDtoRes();
			dataRes.setData(data);
			dataRes.setMessage("Update Success");
			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public GetAllFileDtoRes findAll() throws Exception {
		List<GetAllFileDtoDataRes> datas = new ArrayList<GetAllFileDtoDataRes>();
		List<File> files = fileDao.findAll();

		files.forEach(file -> {
			GetAllFileDtoDataRes data = new GetAllFileDtoDataRes();
			data.setId(file.getId());
			data.setExtensions(file.getExtensions());
			data.setContents(file.getContents());
			data.setIsActive(file.getIsActive());
			data.setVersion(file.getVersion());
			datas.add(data);
		});

		GetAllFileDtoRes dataRes = new GetAllFileDtoRes();
		dataRes.setData(datas);
		return dataRes;
	}

	public GetByIdFileDtoRes findById(String id) throws Exception {
		File getFile = fileDao.findById(id);

		GetByIdFileDtoDataRes data = new GetByIdFileDtoDataRes();
		data.setId(getFile.getId());
		data.setExtensions(getFile.getExtensions());
		data.setContents(getFile.getContents());
		data.setIsActive(getFile.getIsActive());
		data.setVersion(getFile.getVersion());

		GetByIdFileDtoRes dataRes = new GetByIdFileDtoRes();
		dataRes.setData(data);
		return dataRes;
	}

	public DeleteByIdFileDtoRes deleteById(String id) throws Exception {
		DeleteByIdFileDtoRes dataRes = new DeleteByIdFileDtoRes();
		try {
			begin();
			boolean isDeleted = fileDao.deleteById(id);
			commit();

			if (isDeleted) {
				dataRes.setMessage("Delete Success");
			} else {
				throw new Exception("Delete Failed");
			}

			return dataRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

}
