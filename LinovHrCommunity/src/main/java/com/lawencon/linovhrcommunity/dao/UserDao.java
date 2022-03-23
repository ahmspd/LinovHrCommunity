package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.User;

public class UserDao extends BaseDaoImpl<User> {

	public User findById(String id) throws Exception {
		return getById(id);
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

	public List<User> findAll() throws Exception {
		return getAll();
	}

	public User save(User entity) throws Exception {
		return super.save(entity);
	}
}
