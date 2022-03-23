package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.PaymentMethod;

public interface PaymentMethodDao {
	PaymentMethod save(PaymentMethod data) throws Exception;

	PaymentMethod findById(String id) throws Exception;

	List<PaymentMethod> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
