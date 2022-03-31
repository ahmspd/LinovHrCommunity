package com.lawencon.linovhrcommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.EventCoursePaymentDetailDao;
import com.lawencon.linovhrcommunity.dto.eventcoursepaymentdetail.GetAllEventCoursePaymentDetailDtoRes;

@Service
public class EventCoursePaymentDetailService {

	private EventCoursePaymentDetailDao eventCoursePaymentDetailDao;

	@Autowired
	public void setEventCoursePaymentDetailDao(EventCoursePaymentDetailDao eventCoursePaymentDetailDao) {
		this.eventCoursePaymentDetailDao = eventCoursePaymentDetailDao;
	}
	
	public GetAllEventCoursePaymentDetailDtoRes getUnpaidEventCourse(String id) throws Exception {
		GetAllEventCoursePaymentDetailDtoRes result = new GetAllEventCoursePaymentDetailDtoRes();
		result.setData(eventCoursePaymentDetailDao.getUnpaidEventCourse(id));
		
		return result;
	}
}
