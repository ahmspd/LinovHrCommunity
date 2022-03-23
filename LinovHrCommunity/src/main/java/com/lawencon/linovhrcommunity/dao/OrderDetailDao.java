package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.OrderDetail;

@Repository
public class OrderDetailDao extends BaseDaoImpl<OrderDetail> {

	public OrderDetail save(OrderDetail data) throws Exception {
		return super.save(data);
	}

	public OrderDetail findById(String id) throws Exception {
		return getById(id);
	}

	public List<OrderDetail> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}