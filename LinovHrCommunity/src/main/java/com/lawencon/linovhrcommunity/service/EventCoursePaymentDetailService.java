package com.lawencon.linovhrcommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.EventCoursePaymentDao;
import com.lawencon.linovhrcommunity.dao.EventCoursePaymentDetailDao;

@Service
public class EventCoursePaymentDetailService {

	private EventCoursePaymentDao eventCoursePaymentDao;
	private EventCoursePaymentDetailDao eventCoursePaymentDetailDao;

	@Autowired
	public void setEventCoursePaymentDao(EventCoursePaymentDao eventCoursePaymentDao) {
		this.eventCoursePaymentDao = eventCoursePaymentDao;
	}

	@Autowired
	public void setEventCoursePaymentDetailDao(EventCoursePaymentDetailDao eventCoursePaymentDetailDao) {
		this.eventCoursePaymentDetailDao = eventCoursePaymentDetailDao;
	}
	
}
