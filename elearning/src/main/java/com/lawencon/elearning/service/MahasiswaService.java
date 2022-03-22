package com.lawencon.elearning.service;

import java.util.List;

import com.lawencon.elearning.model.Mahasiswa;

public interface MahasiswaService {

	Mahasiswa insert(Mahasiswa data) throws Exception;

	Mahasiswa update(Mahasiswa data) throws Exception;

	Mahasiswa findById(String id) throws Exception;

	List<Mahasiswa> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;

}
