package com.lawencon.linovhrcommunity.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.EventCourse;
import com.lawencon.linovhrcommunity.model.EventCoursePaymentDetail;

@Repository
public class EventCoursePaymentDetailDao extends BaseDaoImpl<EventCoursePaymentDetail> {

	public EventCoursePaymentDetail save(EventCoursePaymentDetail data) throws Exception {
		return super.save(data);
	}

	public EventCoursePaymentDetail findById(String id) throws Exception {
		return getById(id);
	}

	public List<EventCoursePaymentDetail> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	public List<EventCoursePaymentDetail> getUnpaidEventCourse(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, id_event_course ");
		sql.append("FROM t_event_course_payment_detail ");
		sql.append("WHERE created_by = :id AND id_event_course_payment ISNULL;");
		
		List<?> results = createNativeQuery(sql.toString())
				.setParameter("id", id)
				.getResultList();
		
		List<EventCoursePaymentDetail> dataRes = new ArrayList<EventCoursePaymentDetail>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			EventCoursePaymentDetail data = new EventCoursePaymentDetail();
			data.setId(obj[0].toString());
			
			EventCourse dataEventCourse = new EventCourse();
			dataEventCourse.setId(obj[1].toString());
			data.setEventCourse(dataEventCourse);
			
			dataRes.add(data);
		});
		
		return dataRes;
	}

}









