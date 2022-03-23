package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.EventCoursePayment;

public interface EventCoursePaymentDao {
	EventCoursePayment save(EventCoursePayment data) throws Exception;

	EventCoursePayment findById(String id) throws Exception;

	List<EventCoursePayment> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
