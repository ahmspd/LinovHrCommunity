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

	public Integer isPaymentMethodCodeExist(String code) {
		String sql = "select count(id) from t_payment_method where code = :code";
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		Integer res = Integer.valueOf(result.toString());
		return res;
	}
	public Integer isPaymentMethodIdExist(String id) {
		String sql = "select count(id) from t_payment_method where id = :id";
		Object result = createNativeQuery(sql).setParameter("id", id).getSingleResult();
		Integer res = Integer.valueOf(result.toString());
		return res;
	}
}