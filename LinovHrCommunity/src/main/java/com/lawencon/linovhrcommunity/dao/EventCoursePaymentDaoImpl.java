package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.EventCoursePayment;

@Repository
public class EventCoursePaymentDaoImpl extends BaseDaoImpl<EventCoursePayment> implements EventCoursePaymentDao {

	@Override
	public EventCoursePayment save(EventCoursePayment data) throws Exception {
		return super.save(data);
	}

	@Override
	public EventCoursePayment findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<EventCoursePayment> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}