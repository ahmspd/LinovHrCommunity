package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.EventCourse;

@Repository
public class EventCourseDaoImpl extends BaseDaoImpl<EventCourse> implements EventCourseDao {

	@Override
	public EventCourse save(EventCourse data) throws Exception {
		return super.save(data);
	}

	@Override
	public EventCourse findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<EventCourse> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}
