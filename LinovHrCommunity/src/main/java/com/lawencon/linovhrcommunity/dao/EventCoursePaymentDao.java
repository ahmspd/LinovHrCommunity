package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.EventCoursePayment;

public class EventCoursePaymentDao extends BaseDaoImpl<EventCoursePayment> {

	public EventCoursePayment save(EventCoursePayment data) throws Exception {
		return super.save(data);
	}

	public EventCoursePayment findById(String id) throws Exception {
		return getById(id);
	}

	public List<EventCoursePayment> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}
