package com.lawencon.linovhrcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.EventCourseDao;
import com.lawencon.linovhrcommunity.dao.ThreadModelDao;
import com.lawencon.linovhrcommunity.dto.dashboard.GetDashboardDataDtoRes;
import com.lawencon.linovhrcommunity.dto.dashboard.GetDashboardDtoRes;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetAllEventCourseDtoDataRes;

@Service
public class DashboardService extends BaseServiceLinovCommunityImpl {
	private EventCourseDao eventCourseDao;
	private ThreadModelDao threadModelDao;

	@Autowired
	public void setEventCourseDao(EventCourseDao eventCourseDao) {
		this.eventCourseDao = eventCourseDao;
	}

	@Autowired
	public void setThreadModelDao(ThreadModelDao threadModelDao) {
		this.threadModelDao = threadModelDao;
	}

	public GetDashboardDtoRes dataDashboard() throws Exception {
		List<GetAllEventCourseDtoDataRes> listEvent = eventCourseDao.getAllActive("Event");
		List<GetAllEventCourseDtoDataRes> listCourse = eventCourseDao.getAllActive("Course");
		Integer totalThread = threadModelDao.getCountAllThread();
		Integer totalArticle = threadModelDao.getCountThreadByType("2");
		GetDashboardDataDtoRes result = new GetDashboardDataDtoRes();
		result.setEvent(listEvent.size());
		result.setCourse(listCourse.size());
		result.setThread(totalThread);
		result.setArticle(totalArticle);
		GetDashboardDtoRes dataRes = new GetDashboardDtoRes();
		dataRes.setData(result);
		return dataRes;
	}
}
