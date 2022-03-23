package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.Role;

public interface RoleDao {
	Role save(Role data) throws Exception;
	
	Role findById(String id) throws Exception;

	List<Role> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
