package com.lawencon.elearning.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.elearning.model.Mahasiswa;

@Repository
public class MahasiswaDaoImpl extends BaseDaoImpl<Mahasiswa> implements MahasiswaDao {

	@Override
	public Mahasiswa save(Mahasiswa data) throws Exception {
		return super.save(data);
	}

	@Override
	public Mahasiswa findById(UUID id) throws Exception {
		return getById(id);
	}

	@Override
	public List<Mahasiswa> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(UUID id) throws Exception {
		return super.deleteById(id);
	}

}
