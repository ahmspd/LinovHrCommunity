package com.lawencon.linovhrcommunity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_order_detail", uniqueConstraints = { @UniqueConstraint(name = "order_detail_ck", columnNames = { "id",
		"id_event_course" }) })
public class OrderDetail extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;

	@ManyToOne
	@JoinColumn(name = "id_event_course", nullable = false)
	private EventCourse eventCourse;

	@ManyToOne
	@JoinColumn(name = "id_order", nullable = false)
	private Order order;

	@ManyToOne
	@JoinColumn(name = "id_user_member", nullable = false)
	private UserMember userMember;

	public EventCourse getEventCourse() {
		return eventCourse;
	}

	public void setEventCourse(EventCourse eventCourse) {
		this.eventCourse = eventCourse;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public UserMember getUserMember() {
		return userMember;
	}

	public void setUserMember(UserMember userMember) {
		this.userMember = userMember;
	}
}
