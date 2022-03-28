package com.lawencon.linovhrcommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.PollingDetailVoteDao;
import com.lawencon.linovhrcommunity.dto.pollingdetailvote.GetCountPollingVoteDtoRes;
import com.lawencon.linovhrcommunity.dto.pollingdetailvote.InsertPollingVoteDtoDataRes;
import com.lawencon.linovhrcommunity.dto.pollingdetailvote.InsertPollingVoteDtoRes;
import com.lawencon.linovhrcommunity.model.PollingDetailVote;

@Service
public class PollingDetailVoteService extends BaseServiceLinovCommunityImpl{
	private PollingDetailVoteDao pollingDetailVoteDao;

	@Autowired
	public void setPollingDetailVoteDao(PollingDetailVoteDao pollingDetailVoteDao) {
		this.pollingDetailVoteDao = pollingDetailVoteDao;
	}
	
	public InsertPollingVoteDtoRes insert(String id) throws Exception {
		PollingDetailVote dataVote = new PollingDetailVote();
		dataVote.setId(id);
		dataVote.setCreatedBy(getIdFromPrincipal());
		
		pollingDetailVoteDao.save(dataVote);
		InsertPollingVoteDtoDataRes data = new InsertPollingVoteDtoDataRes();
		data.setId(dataVote.getId());
		
		InsertPollingVoteDtoRes result = new InsertPollingVoteDtoRes();
		result.setData(data);
		result.setMessage("success");
		
		return result;
	}
	
	public GetCountPollingVoteDtoRes getVotePolling(String idPolling) throws Exception {
		
		Long total = pollingDetailVoteDao.getCountVote(idPolling);
//		GetCountPollingVoteDtoDataRes dataResult = new GetCountPollingVoteDtoDataRes();
//		dataResult.se
		
		GetCountPollingVoteDtoRes result = new GetCountPollingVoteDtoRes();
		result.setTotal(total);
		
		return result;
	}
}
