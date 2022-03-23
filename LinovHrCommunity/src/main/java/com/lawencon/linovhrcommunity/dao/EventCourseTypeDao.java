package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.EventCourseType;

@Repository
public class EventCourseTypeDao extends BaseDaoImpl<EventCourseType> {

	public EventCourseType save(EventCourseType data) throws Exception {
		return super.save(data);
	}

	public EventCourseType findById(String id) throws Exception {
		return getById(id);
	}

	public List<EventCourseType> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}