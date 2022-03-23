package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Order;

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

}