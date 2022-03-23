package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Role;

@Repository
public class RoleDao extends BaseDaoImpl<Role> {

	public Role save(Role data) throws Exception {
		return super.save(data);
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

	public List<Role> findAll() throws Exception {
		return getAll();
	}

	public Role findById(String id) throws Exception {
		return getById(id);
	}
}
