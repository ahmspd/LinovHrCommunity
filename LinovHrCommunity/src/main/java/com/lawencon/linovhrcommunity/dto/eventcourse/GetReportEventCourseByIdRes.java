package com.lawencon.linovhrcommunity.dto.eventcourse;

import java.util.List;

public class GetReportEventCourseByIdRes {
	private Integer total;
	private Float totalPrice;
	private List<GetReportEventCourseById> data;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<GetReportEventCourseById> getData() {
		return data;
	}

	public void setData(List<GetReportEventCourseById> data) {
		this.data = data;
	}
}
