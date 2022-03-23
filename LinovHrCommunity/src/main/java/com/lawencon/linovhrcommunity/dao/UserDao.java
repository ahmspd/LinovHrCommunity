package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.User;

public interface UserDao {
	User save(User data) throws Exception;
	
	User findById(String id) throws Exception;
	
	List<User> findAll() throws Exception;
	
	boolean deleteById(String id) throws Exception;
}
