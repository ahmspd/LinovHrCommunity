package com.lawencon.linovhrcommunity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_event_course_payment_detail")
public class EventCoursePaymentDetail extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@ManyToOne
	@JoinColumn(name = "id_event_course", nullable = true)
	private EventCourse eventCourse;
	
	@JoinColumn(name = "id_event_course_payment", nullable = true)
	private EventCoursePayment eventCoursePayment;

}
