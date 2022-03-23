package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Profile;

@Repository
public class ProfileDaoImpl extends BaseDaoImpl<Profile> implements ProfileDao {
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

	@Override
	public List<Profile> findAll() throws Exception {
		return getAll();
	}

	@Override
	public Profile findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Profile save(Profile entity) throws Exception {
		return super.save(entity);
	}
}
