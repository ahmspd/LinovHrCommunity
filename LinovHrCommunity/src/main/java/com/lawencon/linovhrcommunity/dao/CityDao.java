package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.City;

@Repository
public class CityDao extends BaseDaoImpl<City> {

	public City save(City data) throws Exception {
		return super.save(data);
	}

	public City findById(String id) throws Exception {
		return getById(id);
	}

	public List<City> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}
