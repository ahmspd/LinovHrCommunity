package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.EventCourse;

@Repository
public class EventCourseDao extends BaseDaoImpl<EventCourse> {

	public EventCourse save(EventCourse data) throws Exception {
		return super.save(data);
	}

	public EventCourse findById(String id) throws Exception {
		return getById(id);
	}

	public List<EventCourse> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}