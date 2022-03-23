package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Profile;

public class ProfileDao extends BaseDaoImpl<Profile> {

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

	public List<Profile> findAll() throws Exception {
		return getAll();
	}

	public Profile findById(String id) throws Exception {
		return getById(id);
	}

	public Profile save(Profile entity) throws Exception {
		return super.save(entity);
	}
}
