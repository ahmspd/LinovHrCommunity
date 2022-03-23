package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.Industry;

public interface IndustryDao {
	Industry save(Industry data) throws Exception;

	Industry findById(String id) throws Exception;

	List<Industry> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
