package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.OrderDetail;

public interface OrderDetailDao {
	OrderDetail save(OrderDetail data) throws Exception;

	OrderDetail findById(String id) throws Exception;

	List<OrderDetail> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
