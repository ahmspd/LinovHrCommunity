package com.lawencon.elearning.dao;

import java.util.List;
import java.util.UUID;

import com.lawencon.elearning.model.Mahasiswa;

public interface MahasiswaDao {

	Mahasiswa save(Mahasiswa data) throws Exception;

	Mahasiswa findById(UUID id) throws Exception;

	List<Mahasiswa> findAll() throws Exception;

	boolean deleteById(UUID id) throws Exception;

}
