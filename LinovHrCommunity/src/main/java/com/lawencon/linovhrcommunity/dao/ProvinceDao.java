package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.Province;

public interface ProvinceDao {
	Province save(Province data) throws Exception;

	Province findById(String id) throws Exception;

	List<Province> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
