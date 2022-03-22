package com.lawencon.linovhrcommunity.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Role;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{
	@Override
	public Role save(Role data) throws Exception {
		return super.save(data);
	}
}
