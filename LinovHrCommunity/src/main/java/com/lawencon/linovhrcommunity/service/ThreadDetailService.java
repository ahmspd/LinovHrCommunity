package com.lawencon.linovhrcommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.ThreadDetailDao;
import com.lawencon.linovhrcommunity.dao.ThreadModelDao;
import com.lawencon.linovhrcommunity.dto.threaddetail.InsertThreadDetailDtoDataRes;
import com.lawencon.linovhrcommunity.dto.threaddetail.InsertThreadDetailDtoReq;
import com.lawencon.linovhrcommunity.dto.threaddetail.InsertThreadDetailDtoRes;
import com.lawencon.linovhrcommunity.model.ThreadDetail;
import com.lawencon.linovhrcommunity.model.ThreadModel;
@Service
public class ThreadDetailService extends BaseServiceLinovCommunityImpl {
	private ThreadDetailDao threadDetailDao;
	private ThreadModelDao threadModelDao;

	@Autowired
	public void setThreadModelDao(ThreadModelDao threadModelDao) {
		this.threadModelDao = threadModelDao;
	}

	@Autowired
	public void setThreadDetailDao(ThreadDetailDao threadDetailDao) {
		this.threadDetailDao = threadDetailDao;
	}
	
	public InsertThreadDetailDtoRes insert(InsertThreadDetailDtoReq data) throws Exception {
		try {
			ThreadModel threadData = threadModelDao.findById(data.getIdThread());
			ThreadDetail newThreadDetail = new ThreadDetail();
			newThreadDetail.setThreadModel(threadData);
			newThreadDetail.setContents(data.getContents());
			newThreadDetail.setCreatedBy(getIdFromPrincipal());
			
			begin();
			newThreadDetail = threadDetailDao.save(newThreadDetail);
			commit();
			
			InsertThreadDetailDtoDataRes dataRes = new InsertThreadDetailDtoDataRes();
			dataRes.setId(newThreadDetail.getId());
			
			InsertThreadDetailDtoRes result = new InsertThreadDetailDtoRes();
			result.setMessage("success");
			result.setData(dataRes);
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
}
