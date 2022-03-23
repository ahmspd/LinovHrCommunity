package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.EventCoursePaymentDetail;

@Repository
public class EventCoursePaymentDetailDaoImpl extends BaseDaoImpl<EventCoursePaymentDetail>
		implements EventCoursePaymentDetailDao {

	@Override
	public EventCoursePaymentDetail save(EventCoursePaymentDetail data) throws Exception {
		return super.save(data);
	}

	@Override
	public EventCoursePaymentDetail findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<EventCoursePaymentDetail> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}