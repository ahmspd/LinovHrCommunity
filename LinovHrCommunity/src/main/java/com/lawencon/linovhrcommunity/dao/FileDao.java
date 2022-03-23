package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.File;

public interface FileDao {
	File save(File data) throws Exception;

	File findById(String id) throws Exception;

	List<File> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
