package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.EventCourse;

public interface EventCourseDao {
	EventCourse save(EventCourse data) throws Exception;

	EventCourse findById(String id) throws Exception;

	List<EventCourse> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
