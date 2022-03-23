package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.PollingDetailVote;

@Repository
public class PollingDetailVoteDaoImpl extends BaseDaoImpl<PollingDetailVote> implements PollingDetailVoteDao {

	@Override
	public PollingDetailVote save(PollingDetailVote data) throws Exception {
		return super.save(data);
	}

	@Override
	public PollingDetailVote findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<PollingDetailVote> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}