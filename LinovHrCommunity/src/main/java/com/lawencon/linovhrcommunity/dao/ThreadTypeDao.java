package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.ThreadType;

@Repository
public class ThreadTypeDao extends BaseDaoImpl<ThreadType> {

	public ThreadType save(ThreadType data) throws Exception {
		return super.save(data);
	}

	public ThreadType findById(String id) throws Exception {
		return getById(id);
	}

	public List<ThreadType> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	public Integer isThreadTypeCodeExist(String code) {
		String sql = "select count(id) from t_thread_type where code = :code";
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		Integer res = Integer.valueOf(result.toString());
		return res;
	}
	public Integer isThreadTypeIdExist(String id) {
		String sql = "select count(id) from t_thread_type where id = :id";
		Object result = createNativeQuery(sql).setParameter("id", id).getSingleResult();
		Integer res = Integer.valueOf(result.toString());
		return res;
	}
}