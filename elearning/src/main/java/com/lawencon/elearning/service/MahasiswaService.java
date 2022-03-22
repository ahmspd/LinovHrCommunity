package com.lawencon.elearning.service;

import java.util.List;
import java.util.UUID;

import com.lawencon.elearning.model.Mahasiswa;

public interface MahasiswaService {

	Mahasiswa insert(Mahasiswa data) throws Exception;

	Mahasiswa update(Mahasiswa data) throws Exception;

	Mahasiswa findById(UUID id) throws Exception;

	List<Mahasiswa> findAll() throws Exception;

	boolean deleteById(UUID id) throws Exception;

}
