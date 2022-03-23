package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.EventCoursePaymentDetail;

public class EventCoursePaymentDetailDao extends BaseDaoImpl<EventCoursePaymentDetail> {

	public EventCoursePaymentDetail save(EventCoursePaymentDetail data) throws Exception {
		return super.save(data);
	}

	public EventCoursePaymentDetail findById(String id) throws Exception {
		return getById(id);
	}

	public List<EventCoursePaymentDetail> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}