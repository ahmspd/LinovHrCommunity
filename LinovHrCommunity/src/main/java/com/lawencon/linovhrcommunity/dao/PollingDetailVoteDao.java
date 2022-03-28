package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.PollingDetailVote;

@Repository
public class PollingDetailVoteDao extends BaseDaoImpl<PollingDetailVote> {

	public PollingDetailVote save(PollingDetailVote data) throws Exception {
		return super.save(data);
	}

	public PollingDetailVote findById(String id) throws Exception {
		return getById(id);
	}

	public List<PollingDetailVote> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

	public Long totalDetailVote(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(tpdv.id) from t_polling_detail_vote tpdv left join t_polling_detail tpd ");
		sql.append("on tpdv.id_polling_detail = tpd.id where tpd.id_polling = :id");

		Object total = createNativeQuery(sql.toString()).setParameter("id", id).getSingleResult();

		return (Long) total;
	}

	public Long getCountVote(String id) throws Exception {
		String sql = "select count(id) as totalId from t_polling_detail_vote tpdv where tpdv.id_polling_detail = :id";

		Object total = createNativeQuery(sql).setParameter("id", id).getSingleResult();

		Object obj = (Object) total;
		
		return Long.valueOf(obj.toString());
	}
}
