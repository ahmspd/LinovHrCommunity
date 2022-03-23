package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Role;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{
	@Override
	public Role save(Role data) throws Exception {
		return super.save(data);
	}
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	@Override
	public List<Role> findAll() throws Exception {
		return getAll();
	}
	@Override
	public Role findById(String id) throws Exception {
		return getById(id);
	}
}
