package com.lawencon.linovhrcommunity.dao;

import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.dto.eventcoursepaymentdetail.GetAllEventCoursePaymentDetailDtoDataRes;
import com.lawencon.linovhrcommunity.model.EventCourse;
import com.lawencon.linovhrcommunity.model.EventCoursePayment;
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
	
	public List<GetAllEventCoursePaymentDetailDtoDataRes> getUnpaidEventCourse(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ecpd.id, ecpd.id_event_course, ect.event_course_type_name, ec.title, ec.date_start, ec.date_end, ec.time_start, ec.time_end, pl.price, ecpd.created_at, ecpd.version, ecpd.is_active ");
		sql.append("FROM t_event_course_payment_detail ecpd ");
		sql.append("INNER JOIN t_event_course ec ON ecpd.id_event_course = ec.id ");
		sql.append("INNER JOIN t_event_course_type ect ON ec.id_event_course_type = ect.id ");
		sql.append("INNER JOIN t_price_list pl ON ec.id_price_list = pl.id ");
		sql.append("WHERE ecpd.created_by = :id AND ecpd.id_event_course_payment ISNULL;");
		
		List<?> results = createNativeQuery(sql.toString())
				.setParameter("id", id)
				.getResultList();
		
		List<GetAllEventCoursePaymentDetailDtoDataRes> dataRes = new ArrayList<GetAllEventCoursePaymentDetailDtoDataRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetAllEventCoursePaymentDetailDtoDataRes reqData = new GetAllEventCoursePaymentDetailDtoDataRes();
			reqData.setId(obj[0].toString());
			reqData.setIdEventCourse(obj[1].toString());
			reqData.setEventCourseType(obj[2].toString());
			reqData.setTitle(obj[3].toString());
			reqData.setDateStart((Date)obj[4]);
			reqData.setDateEnd((Date)obj[5]);
			reqData.setTimeStart((Time)obj[6]);
			reqData.setTimeEnd((Time)obj[7]);
			reqData.setPrice(new BigInteger(obj[8].toString()));
			reqData.setCreatedAt(((Timestamp) obj[9]).toLocalDateTime());
			reqData.setVersion((Integer)obj[10]);
			reqData.setIsActive((Boolean)obj[11]);
			
			dataRes.add(reqData);
		});
		
		return dataRes;
	}
	
	public List<EventCoursePaymentDetail> getByEventCoursePaymentId(String id)  throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, id_event_course, id_event_course_payment, version, is_active ");
		sql.append("FROM t_event_course_payment_detail ");
		sql.append("WHERE id_event_course_payment = :id");
		
		List<?> results = createNativeQuery(sql.toString()).setParameter("id", id).getResultList();
		List<EventCoursePaymentDetail> dataRes = new ArrayList<EventCoursePaymentDetail>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			EventCoursePaymentDetail reqData = new EventCoursePaymentDetail();
			reqData.setId(obj[0].toString());
			
			EventCourse eventCourseData = new EventCourse();
			eventCourseData.setId(obj[1].toString());
			reqData.setEventCourse(eventCourseData);
			
			EventCoursePayment eventCoursePaymentData = new EventCoursePayment();
			eventCoursePaymentData.setId(obj[2].toString());
			reqData.setEventCoursePayment(eventCoursePaymentData);
			
			reqData.setVersion((Integer)obj[3]);
			reqData.setIsActive((Boolean)obj[4]);
			
			dataRes.add(reqData);
		});

		return dataRes;
	}

}









