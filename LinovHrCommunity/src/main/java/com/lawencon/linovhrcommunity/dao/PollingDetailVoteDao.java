package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.PollingDetailVote;

public class PollingDetailVoteDao extends BaseDaoImpl<PollingDetailVote> {

	public PollingDetailVote save(PollingDetailVote data) throws Exception {
		return super.save(data);
	}

	public PollingDetailVote findById(String id) throws Exception {
		return getById(id);
	}

	public List<PollingDetailVote> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}
