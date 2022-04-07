package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.PollingDetailDao;
import com.lawencon.linovhrcommunity.dao.PollingDetailVoteDao;
import com.lawencon.linovhrcommunity.dto.categorydetail.GetCategoryDetailByThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.pollingdetailvote.GetCountPollingVoteDtoDataRes;
import com.lawencon.linovhrcommunity.dto.pollingdetailvote.GetCountPollingVoteDtoRes;
import com.lawencon.linovhrcommunity.dto.pollingdetailvote.GetPollingDtoDataRes;
import com.lawencon.linovhrcommunity.dto.pollingdetailvote.GetPollingDtoRes;
import com.lawencon.linovhrcommunity.dto.pollingdetailvote.InsertPollingVoteDtoDataRes;
import com.lawencon.linovhrcommunity.dto.pollingdetailvote.InsertPollingVoteDtoReq;
import com.lawencon.linovhrcommunity.dto.pollingdetailvote.InsertPollingVoteDtoRes;
import com.lawencon.linovhrcommunity.model.PollingDetail;
import com.lawencon.linovhrcommunity.model.PollingDetailVote;

@Service
public class PollingDetailVoteService extends BaseServiceLinovCommunityImpl {
	private PollingDetailVoteDao pollingDetailVoteDao;
	private PollingDetailDao pollingDetailDao;

	@Autowired
	public void setPollingDetailDao(PollingDetailDao pollingDetailDao) {
		this.pollingDetailDao = pollingDetailDao;
	}

	@Autowired
	public void setPollingDetailVoteDao(PollingDetailVoteDao pollingDetailVoteDao) {
		this.pollingDetailVoteDao = pollingDetailVoteDao;
	}

	public InsertPollingVoteDtoRes insert(InsertPollingVoteDtoReq id) throws Exception {
		PollingDetail dataPollingDetail = pollingDetailDao.findById(id.getIdPollingDetail());

		PollingDetailVote dataVote = new PollingDetailVote();
		dataVote.setPollingDetail(dataPollingDetail);
		dataVote.setCreatedBy(getIdFromPrincipal());

		try {
			begin();
			pollingDetailVoteDao.save(dataVote);
			commit();
			InsertPollingVoteDtoDataRes data = new InsertPollingVoteDtoDataRes();
			data.setId(dataVote.getId());

			InsertPollingVoteDtoRes result = new InsertPollingVoteDtoRes();
			result.setData(data);
			result.setMessage("success");

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public GetCountPollingVoteDtoRes getVotePolling(String idPolling) throws Exception {

		Integer total = pollingDetailVoteDao.totalDetailVote(idPolling);
		List<PollingDetail> pollingDetailList = pollingDetailDao.findByIdPolling(idPolling);
		GetCountPollingVoteDtoRes result = new GetCountPollingVoteDtoRes();
		result.setTotal(total);
		System.out.println("sopandi " + pollingDetailList.get(0).getId());
		List<GetCountPollingVoteDtoDataRes> dataTotal = new ArrayList<GetCountPollingVoteDtoDataRes>();
		for (int i = 0; i < pollingDetailList.size(); i++) {
			Integer totalVote = pollingDetailVoteDao.getCountVote(pollingDetailList.get(i).getId());
			GetCountPollingVoteDtoDataRes datas = new GetCountPollingVoteDtoDataRes();
			datas.setTotal(totalVote);
			datas.setName(pollingDetailList.get(i).getPollingName());
			datas.setId(pollingDetailList.get(i).getId());
			dataTotal.add(datas);
			result.setData(dataTotal);
		}

		return result;
	}

	public GetPollingDtoRes getVote(String idUser, String idPollingDetail) throws Exception {
		PollingDetailVote data = pollingDetailVoteDao.getVote(idUser, idPollingDetail);
		GetPollingDtoRes result = new GetPollingDtoRes();
		try {
			GetPollingDtoDataRes dataRes = new GetPollingDtoDataRes();
			dataRes.setId(data.getId());
			result.setData(dataRes);
		} catch (NullPointerException e) {

		}
		return result;
	}
}
