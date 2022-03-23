package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.File;

@Repository
public class FileDao extends BaseDaoImpl<File> {

	public File save(File data) throws Exception {
		return super.save(data);
	}

	public File findById(String id) throws Exception {
		return getById(id);
	}

	public List<File> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}