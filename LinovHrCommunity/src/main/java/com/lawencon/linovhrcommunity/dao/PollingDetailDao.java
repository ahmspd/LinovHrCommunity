package com.lawencon.linovhrcommunity.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.PollingDetail;

@Repository
public class PollingDetailDao extends BaseDaoImpl<PollingDetail> {

	public PollingDetail save(PollingDetail data) throws Exception {
		return super.save(data);
	}

	public PollingDetail findById(String id) throws Exception {
		return getById(id);
	}

	public List<PollingDetail> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

	public List<PollingDetail> findByIdPolling(String id) throws Exception {
		String sql = "select id from t_polling_detail tpd where tpd.id_polling = :id";
		List<?> results = createNativeQuery(sql).setParameter("id", id).getResultList();
		List<PollingDetail> dataRes = new ArrayList<PollingDetail>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			PollingDetail reqData = new PollingDetail();
			reqData.setId(obj[0].toString());
			dataRes.add(reqData);
		});
		return dataRes;
	}
}
