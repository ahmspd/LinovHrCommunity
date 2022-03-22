package com.lawencon.linovhrcommunity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(
				name = "order_detail_ck",
				columnNames = {"id", "id_event_course"}
		)
}, name="t_order_detail")
public class OrderDetail extends BaseEntity{
	private static final long serialVersionUID = -5196455701225322056L;
	
	@ManyToOne
	@JoinColumn(name = "id_event_course", nullable = false)
	private EventCourse idEventCourse;
	
	@ManyToOne
	@JoinColumn(name = "id_order", nullable = false)
	private Order idOrder;
	
	@ManyToOne
	@JoinColumn(name = "id_user_member", nullable = false)
	private UserMember idUserMember;

	public EventCourse getIdEventCourse() {
		return idEventCourse;
	}

	public void setIdEventCourse(EventCourse idEventCourse) {
		this.idEventCourse = idEventCourse;
	}

	public Order getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Order idOrder) {
		this.idOrder = idOrder;
	}

	public UserMember getIdUserMember() {
		return idUserMember;
	}

	public void setIdUserMember(UserMember idUserMember) {
		this.idUserMember = idUserMember;
	}
}
