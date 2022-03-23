package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.PollingDetailVote;

public interface PollingDetailVoteDao {
	PollingDetailVote save(PollingDetailVote data) throws Exception;

	PollingDetailVote findById(String id) throws Exception;

	List<PollingDetailVote> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
