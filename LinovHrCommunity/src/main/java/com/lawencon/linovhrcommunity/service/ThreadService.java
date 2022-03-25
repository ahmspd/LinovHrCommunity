package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.PollingDao;
import com.lawencon.linovhrcommunity.dao.PollingDetailDao;
import com.lawencon.linovhrcommunity.dao.ThreadModelDao;
import com.lawencon.linovhrcommunity.dao.ThreadTypeDao;
import com.lawencon.linovhrcommunity.dto.pollingdetail.InsertPollingDetailDtoReq;
import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoDataRes;
import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoReq;
import com.lawencon.linovhrcommunity.dto.thread.InsertThreadDtoRes;
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

	public InsertThreadDtoRes insert(InsertThreadDtoReq data) throws Exception {
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
}
