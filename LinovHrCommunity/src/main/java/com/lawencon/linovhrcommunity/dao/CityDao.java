package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.City;

public interface CityDao {
	City save(City data) throws Exception;

	City findById(String id) throws Exception;

	List<City> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
