package com.lawencon.linovhrcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.linovhrcommunity.constant.ThreadTypeCode;
import com.lawencon.linovhrcommunity.dao.CategoryDao;
import com.lawencon.linovhrcommunity.dao.CategoryDetailDao;
import com.lawencon.linovhrcommunity.dao.FileDao;
import com.lawencon.linovhrcommunity.dao.PollingDao;
import com.lawencon.linovhrcommunity.dao.PollingDetailDao;
import com.lawencon.linovhrcommunity.dao.ThreadDetailDao;
import com.lawencon.linovhrcommunity.dao.ThreadModelDao;
import com.lawencon.linovhrcommunity.dao.ThreadTypeDao;
import com.lawencon.linovhrcommunity.dto.category.InsertCategoryDtoDataRes;
import com.lawencon.linovhrcommunity.dto.categorydetail.GetCategoryDetailByThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.pollingdetail.GetPollingDetailByPollingIdDto;
import com.lawencon.linovhrcommunity.dto.pollingdetail.InsertPollingDetailDtoReq;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDataDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDetailDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadPollingDetailDtoDataRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadPollingDetailDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadPollingDtoDataRes;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadPollingDtoRes;
import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoDataRes;
import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoReq;
import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.threaddetail.GetThreadDetailDataDtoRes;
import com.lawencon.linovhrcommunity.model.Category;
import com.lawencon.linovhrcommunity.model.CategoryDetail;
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
	private CategoryDetailDao categoryDetailDao;
	private CategoryDao categoryDao;
	private ThreadDetailDao threadDetailDao;

	@Autowired
	public void setThreadDetailDao(ThreadDetailDao threadDetailDao) {
		this.threadDetailDao = threadDetailDao;
	}

	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Autowired
	public void setCategoryDetailDao(CategoryDetailDao categoryDetailDao) {
		this.categoryDetailDao = categoryDetailDao;
	}

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
		ThreadType threadType = threadTypeDao.getById(data.getIdThreadType());

		ThreadModel dataThread = new ThreadModel();
		dataThread.setTitle(data.getTitle());
		dataThread.setContents(data.getContents());
		dataThread.setThreadType(threadType);
		dataThread.setIsPremium(data.getIsPremium());
		dataThread.setCreatedBy(getIdFromPrincipal());
		try {
			begin();
			if(file != null) {
				File dataFile = new File();
				String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
						file.getOriginalFilename().length());

				dataFile.setExtensions(extName);
				dataFile.setContents(file.getBytes());
				dataFile.setCreatedBy(getIdFromPrincipal());
				dataFile = fileDao.save(dataFile);
				dataThread.setFile(dataFile);
			}
			dataThread = threadDao.save(dataThread);
			if (threadType.getCode().equals(ThreadTypeCode.POLLING.getDetail())) {
				Polling dataPolling = new Polling();
				dataPolling.setThreadModel(dataThread);
				dataPolling.setPollingName(data.getPollingName());
				dataPolling.setCreatedBy(getIdFromPrincipal());

				Polling pollingSave = pollingDao.save(dataPolling);

				List<InsertPollingDetailDtoReq> listPollingDetail = data.getDataPolling();

				for (int i = 0; i < listPollingDetail.size(); i++) {
					PollingDetail dataPollingDetail = new PollingDetail();
					dataPollingDetail.setPolling(pollingSave);
					dataPollingDetail.setPollingName(listPollingDetail.get(i).getPollingName());
					dataPollingDetail.setCreatedBy(getIdFromPrincipal());
					pollingDetailDao.save(dataPollingDetail);
				}
			}
			
			List<InsertCategoryDtoDataRes> listCategory = data.getDataCategory();
			for(int i=0; i<listCategory.size();i++) {
				Category dataCategory = new Category();
				dataCategory = categoryDao.getById(listCategory.get(i).getId());
				CategoryDetail dataCategoryDetail = new CategoryDetail();
				dataCategoryDetail.setCategory(dataCategory);
				dataCategoryDetail.setThreadModel(dataThread);
				dataCategoryDetail.setCreatedBy(getIdFromPrincipal());
				dataCategoryDetail = categoryDetailDao.save(dataCategoryDetail);
			}
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		InsertThreadDtoDataRes resultData = new InsertThreadDtoDataRes();
		resultData.setId(dataThread.getId());

		InsertThreadDtoRes result = new InsertThreadDtoRes();
		result.setData(resultData);
		result.setMessage("Success");

		return result;
	}
	
	public GetThreadDtoRes getAllThread() throws Exception {
		List<GetThreadDataDtoRes> data = threadDao.getAllThread();
		for(int i =0; i<data.size();i++) {
			List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao.getCategoryDetailByThread(data.get(i).getId());
			data.get(i).setDataCategoryDetail(categoryDetail);
		}
		GetThreadDtoRes result = new GetThreadDtoRes();
		result.setData(data);
		
		return result;
	}
	
	public GetThreadDtoRes getThreadByUser(String idUser) throws Exception {
		List<GetThreadDataDtoRes> data = threadDao.getThreadByUser(idUser);
		for(int i =0; i<data.size();i++) {
			List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao.getCategoryDetailByThread(data.get(i).getId());
			data.get(i).setDataCategoryDetail(categoryDetail);
		}
		GetThreadDtoRes result = new GetThreadDtoRes();
		result.setData(data);
		
		return result;
	}
	
	public GetThreadDtoRes getThreadPremium(Boolean isPremium) throws Exception {
		List<GetThreadDataDtoRes> data = threadDao.getThreadPremium(isPremium);
		for(int i =0; i<data.size();i++) {
			List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao.getCategoryDetailByThread(data.get(i).getId());
			data.get(i).setDataCategoryDetail(categoryDetail);
		}
		GetThreadDtoRes result = new GetThreadDtoRes();
		result.setData(data);
		
		return result;
	}
	
	public GetThreadDtoRes getThreadByType(String idType) throws Exception {
		List<GetThreadDataDtoRes> data = threadDao.getThreadByType(idType);
		for(int i =0; i<data.size();i++) {
			List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao.getCategoryDetailByThread(data.get(i).getId());
			data.get(i).setDataCategoryDetail(categoryDetail);
		}
		GetThreadDtoRes result = new GetThreadDtoRes();
		result.setData(data);
		
		return result;
	}
	
	public GetThreadPollingDtoRes getAllThreadPolling() throws Exception {
		List<GetThreadPollingDtoDataRes> data = threadDao.getAllThreadPolling(ThreadTypeCode.POLLING.getDetail());
		for(int i =0; i<data.size();i++) {
			List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao.getCategoryDetailByThread(data.get(i).getId());
			data.get(i).setDataCategoryDetail(categoryDetail);
		}
		GetThreadPollingDtoRes result = new GetThreadPollingDtoRes();
		result.setData(data);
		
		return result;
	}
	
	public GetThreadPollingDtoRes getThreadPollingByUser(String idUser) throws Exception {
		List<GetThreadPollingDtoDataRes> data = threadDao.getAllThreadPollingByUser(ThreadTypeCode.POLLING.getDetail(), idUser);
		for(int i =0; i<data.size();i++) {
			List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao.getCategoryDetailByThread(data.get(i).getId());
			data.get(i).setDataCategoryDetail(categoryDetail);
		}
		GetThreadPollingDtoRes result = new GetThreadPollingDtoRes();
		result.setData(data);
		
		return result;
	}

	public GetThreadPollingDetailDtoRes getThreadPollingById(String idUser) throws Exception {
		GetThreadPollingDetailDtoDataRes data = threadDao.getDetailThreadPolling(ThreadTypeCode.POLLING.getDetail(), idUser);
		List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao.getCategoryDetailByThread(data.getId());
		List<GetPollingDetailByPollingIdDto> pollingDetail = pollingDetailDao.getPollingDetailByIdPolling(data.getIdPolling());
		data.setDataCategoryDetail(categoryDetail);
		data.setDataPollingDetail(pollingDetail);
		GetThreadPollingDetailDtoRes result = new GetThreadPollingDetailDtoRes();
		result.setData(data);
		return result;
	}
	
	public GetThreadDetailDtoRes getThreadDetail(String idThread) throws Exception {
		GetThreadDataDtoRes data = threadDao.getThreadDetail(idThread);
		List<GetThreadDetailDataDtoRes> threadComment = threadDetailDao.getThreadDetailData(idThread);
		List<GetCategoryDetailByThreadDtoRes> categoryDetail = categoryDetailDao.getCategoryDetailByThread(data.getId());
		data.setDataCategoryDetail(categoryDetail);
		data.setDataThreadComment(threadComment);
		GetThreadDetailDtoRes result = new GetThreadDetailDtoRes();
		result.setData(data);
		return result;
	}
}
