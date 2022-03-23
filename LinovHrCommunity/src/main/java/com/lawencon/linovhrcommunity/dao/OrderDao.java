package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.Order;

public interface OrderDao {
	Order save(Order data) throws Exception;

	Order findById(String id) throws Exception;

	List<Order> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
