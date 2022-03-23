package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.PaymentMethod;

@Repository
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