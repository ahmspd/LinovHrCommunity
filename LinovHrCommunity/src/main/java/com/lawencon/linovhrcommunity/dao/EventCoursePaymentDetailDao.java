package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.EventCoursePaymentDetail;

public interface EventCoursePaymentDetailDao {
	EventCoursePaymentDetail save(EventCoursePaymentDetail data) throws Exception;

	EventCoursePaymentDetail findById(String id) throws Exception;

	List<EventCoursePaymentDetail> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
