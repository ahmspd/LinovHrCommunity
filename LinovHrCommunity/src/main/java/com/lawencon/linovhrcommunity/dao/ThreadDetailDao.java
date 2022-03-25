package com.lawencon.linovhrcommunity.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.dto.thread.GetThreadDataDtoRes;
import com.lawencon.linovhrcommunity.dto.threaddetail.GetThreadDetailDataDtoRes;
import com.lawencon.linovhrcommunity.model.ThreadDetail;

@Repository
public class ThreadDetailDao extends BaseDaoImpl<ThreadDetail> {

	public ThreadDetail save(ThreadDetail data) throws Exception {
		return super.save(data);
	}

	public ThreadDetail findById(String id) throws Exception {
		return getById(id);
	}

	public List<ThreadDetail> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

	public List<GetThreadDetailDataDtoRes> getThreadDetailData(String idThread) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select ttd.id , ttd.id_thread , ttd.contents , ttd.created_by, ttd.created_at , tp.full_name ");
		sql.append("from t_thread_detail ttd left join t_user tu  on ttd.created_by = tu.id ");
		sql.append("left join t_profile tp on tu.id = tp.id_user where ttd.id_thread = :idThread");

		List<?> results = createNativeQuery(sql.toString()).setParameter("idThread", idThread).getResultList();
		List<GetThreadDetailDataDtoRes> dataRes = new ArrayList<GetThreadDetailDataDtoRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetThreadDetailDataDtoRes resData = new GetThreadDetailDataDtoRes();
			resData.setId(obj[0].toString());
			resData.setIdThread(obj[1].toString());
			resData.setContents(obj[2].toString());
			resData.setCreatedBy(obj[3].toString());
			resData.setCreatedAt(obj[4].toString());
			resData.setFullName(obj[5].toString());
			dataRes.add(resData);
		});
		return dataRes;
	}
}
