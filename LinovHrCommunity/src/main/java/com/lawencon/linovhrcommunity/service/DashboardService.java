package com.lawencon.linovhrcommunity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.EventCourseDao;
import com.lawencon.linovhrcommunity.dto.dashboard.GetDashboardDataDtoRes;
import com.lawencon.linovhrcommunity.dto.dashboard.GetDashboardDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetAllEventCourseDtoDataRes;

@Service
public class DashboardService extends BaseServiceLinovCommunityImpl {
	private EventCourseDao eventCourseDao;
	
	public GetDashboardDtoRes dataDashboard() throws Exception {
		List<GetAllEventCourseDtoDataRes> listEvent = eventCourseDao.getAllActive("Event");
		List<GetAllEventCourseDtoDataRes> listCourse = eventCourseDao.getAllActive("Course");

		GetDashboardDataDtoRes result = new GetDashboardDataDtoRes();
		result.setEvent(listEvent.size());
		result.setCourse(listCourse.size());
//		private Integer Event
//		private Integer Course
//		private Integer thread
//		private Integer article
//		private Integer user
//		private Integer userPremium
		return null;
	}
}
