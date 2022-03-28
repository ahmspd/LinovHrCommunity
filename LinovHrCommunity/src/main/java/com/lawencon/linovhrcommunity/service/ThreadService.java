package com.lawencon.linovhrcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.linovhrcommunity.dao.FileDao;
import com.lawencon.linovhrcommunity.dao.PollingDao;
import com.lawencon.linovhrcommunity.dao.PollingDetailDao;
import com.lawencon.linovhrcommunity.dao.ThreadModelDao;
import com.lawencon.linovhrcommunity.dao.ThreadTypeDao;
import com.lawencon.linovhrcommunity.dto.pollingdetail.InsertPollingDetailDtoReq;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDataDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadPollingDtoDataRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadPollingDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoDataRes;
import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoReq;
import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoRes;
import com.lawencon.linovhrcommunity.model.File;
import com.lawencon.linovhrcommunity.model.Polling;
import com.lawencon.linovhrcommunity.model.PollingDetail;
import com.lawencon.linovhrcommunity.model.ThreadModel;
import com.lawencon.linovhrcommunity.model.ThreadType;

@Service
public class ThreadService extends BaseServiceLinovCommunityImpl {

	private ThreadTypeDao threadTypeDao;
	private ThreadModelDao threadDao;
	private PollingDao pollingDao;
	private PollingDetailDao pollingDetailDao;
	private FileDao fileDao;

	@Autowired
	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Autowired
	public void setPollingDetailDao(PollingDetailDao pollingDetailDao) {
		this.pollingDetailDao = pollingDetailDao;
	}

	@Autowired
	public void setPollingDao(PollingDao pollingDao) {
		this.pollingDao = pollingDao;
	}

	@Autowired
	public void setThreadDao(ThreadModelDao threadDao) {
		this.threadDao = threadDao;
	}

	@Autowired
	public void setThreadTypeDao(ThreadTypeDao threadTypeDao) {
		this.threadTypeDao = threadTypeDao;
	}

	public InsertThreadDtoRes insert(String content, MultipartFile file) throws Exception {
		InsertThreadDtoReq data = new ObjectMapper().readValue(content, InsertThreadDtoReq.class);
		ThreadType threadType = new ThreadType();
		threadType.setId(data.getIdThreadType());
		threadType.setVersion(0);

		ThreadModel dataThread = new ThreadModel();
		dataThread.setTitle(data.getTitle());
		dataThread.setContents(data.getContents());
		dataThread.setThreadType(threadType);
		dataThread.setIsPremium(data.getIsPremium());
		dataThread.setCreatedBy(getIdFromPrincipal());

		ThreadType dataType = threadTypeDao.getById(data.getIdThreadType());
		ThreadModel threadSave = new ThreadModel();

		try {
			begin();
			File fileSave = new File();
			if(file != null) {
				File dataFile = new File();
				String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
						file.getOriginalFilename().length());

				dataFile.setExtensions(extName);
				dataFile.setContents(file.getBytes());
				dataFile.setCreatedBy(getIdFromPrincipal());
				fileSave = fileDao.save(dataFile);
			}
			dataThread.setFile(fileSave);
			threadSave = threadDao.save(dataThread);
			if (dataType.getCode().equals("PL0001")) {
				Polling dataPolling = new Polling();
				dataPolling.setThreadModel(threadSave);
				dataPolling.setPollingName(data.getPollingName());
				dataPolling.setCreatedBy(getIdFromPrincipal());

				Polling pollingSave = pollingDao.save(dataPolling);

				List<InsertPollingDetailDtoReq> listPollingDetail = data.getData();

				for (int i = 0; i < listPollingDetail.size(); i++) {
					PollingDetail dataPollingDetail = new PollingDetail();
					dataPollingDetail.setPolling(pollingSave);
					dataPollingDetail.setPollingName(listPollingDetail.get(i).getPollingName());
					dataPollingDetail.setCreatedBy(getIdFromPrincipal());
					pollingDetailDao.save(dataPollingDetail);
				}
			}
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		InsertThreadDtoDataRes resultData = new InsertThreadDtoDataRes();
		resultData.setId(threadSave.getId());

		InsertThreadDtoRes result = new InsertThreadDtoRes();
		result.setData(resultData);
		result.setMessage("Success");

		return result;
	}
	
	public GetThreadDtoRes getAllThread() throws Exception {
		List<GetThreadDataDtoRes> data = threadDao.getAllThread();
		GetThreadDtoRes result = new GetThreadDtoRes();
		result.setData(data);
		
		return result;
	}
	
	public GetThreadDtoRes getThreadByUser(String idUser) throws Exception {
		List<GetThreadDataDtoRes> data = threadDao.getThreadByUser(idUser);
		GetThreadDtoRes result = new GetThreadDtoRes();
		result.setData(data);
		
		return result;
	}
	
	public GetThreadDtoRes getThreadPremium(Boolean isPremium) throws Exception {
		List<GetThreadDataDtoRes> data = threadDao.getThreadPremium(isPremium);
		GetThreadDtoRes result = new GetThreadDtoRes();
		result.setData(data);
		
		return result;
	}
	
	public GetThreadDtoRes getThreadByType(String idType) throws Exception {
		List<GetThreadDataDtoRes> data = threadDao.getThreadByType(idType);
		GetThreadDtoRes result = new GetThreadDtoRes();
		result.setData(data);
		
		return result;
	}
	
	public GetThreadPollingDtoRes getAllThreadPolling() throws Exception {
		List<GetThreadPollingDtoDataRes> data = threadDao.getAllThreadPolling();
		GetThreadPollingDtoRes result = new GetThreadPollingDtoRes();
		result.setData(data);
		
		return result;
	}
	
	public GetThreadPollingDtoRes getThreadPollingByUser(String idUser) throws Exception {
		List<GetThreadPollingDtoDataRes> data = threadDao.getAllThreadPollingByUser(idUser);
		GetThreadPollingDtoRes result = new GetThreadPollingDtoRes();
		result.setData(data);
		
		return result;
	}
}
