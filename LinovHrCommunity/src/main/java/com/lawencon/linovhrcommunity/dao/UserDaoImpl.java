package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
	@Override
	public User findById(String id) throws Exception {
		return getById(id);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public List<User> findAll() throws Exception {
		return getAll();
	}
	
	@Override
	public User save(User entity) throws Exception {
		return super.save(entity);
	}
}
