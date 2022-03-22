package com.lawencon.elearning.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.elearning.dao.MahasiswaDao;
import com.lawencon.elearning.model.Mahasiswa;

@Service
public class MahasiswaServiceImpl extends BaseServiceImpl implements MahasiswaService {

	@Autowired
	private MahasiswaDao mahasiswaDao;

	@Override
	public Mahasiswa insert(Mahasiswa data) throws Exception {
		try {
			begin();
			mahasiswaDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}

	@Override
	public Mahasiswa update(Mahasiswa data) throws Exception {
		try {
			Mahasiswa mhsDb = findById(data.getId());
			data.setCreatedAt(mhsDb.getCreatedAt());
			data.setCreatedBy(mhsDb.getCreatedBy());

			begin();
			mahasiswaDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}

	@Override
	public Mahasiswa findById(UUID id) throws Exception {
		return mahasiswaDao.findById(id);
	}

	@Override
	public List<Mahasiswa> findAll() throws Exception {
		return mahasiswaDao.findAll();
	}

	@Override
	public boolean deleteById(UUID id) throws Exception {
		try {
			begin();
			boolean isDeleted = mahasiswaDao.deleteById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

}
