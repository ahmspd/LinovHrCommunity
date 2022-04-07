package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import javax.persistence.NoResultException;

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

	public Integer totalDetailVote(String idPolling) throws Exception {
		String sql = "select count(tpdv.id) from t_polling_detail_vote tpdv left join t_polling_detail tpd on tpdv.id_polling_detail = tpd.id where tpd.id_polling = :idPolling";

//		Object total = createNativeQuery(sql.toString()).setParameter("id", id).getSingleResult();
//
//		return (Long) total;
		Object result = null;
		Integer res = 0;
		try {
			result = createNativeQuery(sql)
					.setParameter("idPolling", idPolling)
					.getSingleResult();
			res = Integer.valueOf(result.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Integer getCountVote(String id) throws Exception {
		String sql = "select count(tpdv.id) as totalId from t_polling_detail_vote tpdv where tpdv.id_polling_detail = :id";

		Object result = null;
		Integer res = 0;
		try {
			result = createNativeQuery(sql)
					.setParameter("id", id)
					.getSingleResult();
			res = Integer.valueOf(result.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return res;
		
//		Object total = createNativeQuery(sql).setParameter("id", id).getSingleResult();
//
//		Object obj = (Object) total;
//		
//		return Long.valueOf(obj.toString());
	}
	
	public PollingDetailVote getVote(String idUser, String idPollingDetail) throws Exception {
		String sql = "select tpdv.id, tpdv.id_polling_detail from t_polling_detail_vote tpdv where tpdv.id_polling_detail = :idPollingDetail and tpdv.created_by = :idUser";
		
		PollingDetailVote vote = new PollingDetailVote();
		Object result = null;
		try {
			result = createNativeQuery(sql)
					.setParameter("idUser", idUser)
					.setParameter("idPollingDetail", idPollingDetail)
					.getSingleResult();
		} catch (NoResultException e){
		}
		Object[] obj = (Object[]) result;
		if(obj!=null) {
			vote.setId((obj[0] != null) ? obj[0].toString() : null);
		}
		return vote;
	}
}
