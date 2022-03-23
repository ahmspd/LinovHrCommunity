package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.UserMember;

public interface UserMemberDao {
	UserMember save(UserMember data) throws Exception;

	UserMember findById(String id) throws Exception;

	List<UserMember> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
