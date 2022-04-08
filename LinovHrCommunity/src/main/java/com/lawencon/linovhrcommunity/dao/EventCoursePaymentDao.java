package com.lawencon.linovhrcommunity.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.GetAllEventCoursePaymentDtoDataRes;
import com.lawencon.linovhrcommunity.model.EventCoursePayment;

@Repository
public class EventCoursePaymentDao extends BaseDaoImpl<EventCoursePayment> {

	public EventCoursePayment save(EventCoursePayment data) throws Exception {
		return super.save(data);
	}

	public EventCoursePayment findById(String id) throws Exception {
		return getById(id);
	}

	public List<EventCoursePayment> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

	public List<GetAllEventCoursePaymentDtoDataRes> getAllUnaccepted() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ecp.id, ecp.id_file, ecp.total_price, pm.payment_name, p.full_name, p.phone_number, u.email, ecp.created_at, ecp.version, ecp.is_active ");
		sql.append("FROM t_event_course_payment ecp ");
		sql.append("INNER JOIN t_payment_method pm ON ecp.id_payment_method = pm.id ");
		sql.append("INNER JOIN t_user u ON ecp.created_by = u.id ");
		sql.append("INNER JOIN t_profile p ON u.id = p.id_user ");
		sql.append("WHERE ecp.is_accept = false ");
		
		List<?> results = createNativeQuery(sql.toString()).getResultList();
		List<GetAllEventCoursePaymentDtoDataRes> dataRes = new ArrayList<GetAllEventCoursePaymentDtoDataRes>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetAllEventCoursePaymentDtoDataRes reqData = new GetAllEventCoursePaymentDtoDataRes();
			reqData.setId(obj[0].toString());
			reqData.setIdFile((obj[1] != null)?obj[1].toString():null);
			reqData.setTotalprice(new BigInteger(obj[2].toString()));
			reqData.setPaymentName(obj[3].toString());
			reqData.setFullName(obj[4].toString());
			reqData.setPhoneNumber(obj[5].toString());
			reqData.setEmail(obj[6].toString());
			reqData.setCreatedAt(((Timestamp) obj[7]).toLocalDateTime());
			reqData.setVersion((Integer)obj[8]);
			reqData.setIsActive((Boolean)obj[9]);
			
			dataRes.add(reqData);
		});
		
		return dataRes;
	}
}
