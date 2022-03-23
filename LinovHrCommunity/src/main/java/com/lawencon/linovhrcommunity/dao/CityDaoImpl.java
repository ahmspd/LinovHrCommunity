package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.City;

@Repository
public class CityDaoImpl extends BaseDaoImpl<City> implements CityDao {

	@Override
	public City save(City data) throws Exception {
		return super.save(data);
	}

	@Override
	public City findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<City> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}
