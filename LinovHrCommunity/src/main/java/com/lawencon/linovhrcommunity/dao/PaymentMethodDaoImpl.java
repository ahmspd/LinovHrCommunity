package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.PaymentMethod;

@Repository
public class PaymentMethodDaoImpl extends BaseDaoImpl<PaymentMethod> implements PaymentMethodDao {

	@Override
	public PaymentMethod save(PaymentMethod data) throws Exception {
		return super.save(data);
	}

	@Override
	public PaymentMethod findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<PaymentMethod> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}