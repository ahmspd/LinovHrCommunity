package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Order;

@Repository
public class OrderDao extends BaseDaoImpl<Order> {

	public Order save(Order data) throws Exception {
		return super.save(data);
	}

	public Order findById(String id) throws Exception {
		return getById(id);
	}

	public List<Order> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	public Long getOrderMember(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select to2.is_accept , to2.id_file , to2.invoice ");
		sql.append("from t_order to2 where to2.id_user = :id");
		
		Object result = null;
		Long stat = 0L;
		try {
			result = createNativeQuery(sql.toString()).setParameter("id", id)
					.getSingleResult();

		} catch (NoResultException e) {
		}
		Object[] obj = (Object[]) result;
		if(obj!=null) {			
			if(Boolean.valueOf(obj[0].toString()) == false && obj[1]==null) {
				stat = 1L;
			}
			if(Boolean.valueOf(obj[0].toString()) == false && obj[1]!=null) {
				stat = 2L;
			}
			if(Boolean.valueOf(obj[0].toString()) == true && obj[1]!=null && obj[2]!=null) {
				stat = 3L;
			}
		}
		
		return stat;
	}
	public String getIdOrderMember(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select to2.id , to2.id_file , to2.invoice ");
		sql.append("from t_order to2 where to2.id_user = :id");
		
		Object result = null;
		String stat = "";
		try {
			result = createNativeQuery(sql.toString()).setParameter("id", id)
					.getSingleResult();

		} catch (NoResultException e) {
		}
		Object[] obj = (Object[]) result;
		if(obj!=null) {			
			if(obj[0]!=null) {
				stat = obj[0].toString();
			}
		}
		
		return stat;
	}

}