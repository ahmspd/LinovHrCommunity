package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.UserMember;

@Repository
public class UserMemberDaoImpl extends BaseDaoImpl<UserMember> implements UserMemberDao {

	@Override
	public UserMember save(UserMember data) throws Exception {
		return super.save(data);
	}

	@Override
	public UserMember findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<UserMember> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}