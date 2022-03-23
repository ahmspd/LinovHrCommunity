package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.UserMember;

public class UserMemberDao extends BaseDaoImpl<UserMember> {

	public UserMember save(UserMember data) throws Exception {
		return super.save(data);
	}

	public UserMember findById(String id) throws Exception {
		return getById(id);
	}

	public List<UserMember> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}