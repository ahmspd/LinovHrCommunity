package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.Profile;

public interface ProfileDao {
	Profile save(Profile data) throws Exception;
	
	Profile findById(String id) throws Exception;
	
	List<Profile> findAll() throws Exception;
	
	boolean deleteById(String id) throws Exception;
}
