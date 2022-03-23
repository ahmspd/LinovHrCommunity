package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.PaymentMethod;

public class PaymentMethodDao extends BaseDaoImpl<PaymentMethod> {

	public PaymentMethod save(PaymentMethod data) throws Exception {
		return super.save(data);
	}

	public PaymentMethod findById(String id) throws Exception {
		return getById(id);
	}

	public List<PaymentMethod> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}