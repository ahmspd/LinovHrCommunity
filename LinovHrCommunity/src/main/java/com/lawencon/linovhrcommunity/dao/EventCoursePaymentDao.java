package com.lawencon.linovhrcommunity.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetReportEventCourseById;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.GetAllEventCoursePaymentDtoDataRes;
import com.lawencon.linovhrcommunity.dto.eventcoursepayment.GetReportEventCoursePaymentDtoDataRes;
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

	public List<GetAllEventCoursePaymentDtoDataRes> getAllUnaccepted(Boolean isAccept, int startPage, int maxPage) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ecp.id, ecp.id_file, ecp.total_price, pm.payment_name, p.full_name, p.phone_number, u.email, ecp.created_at, ecp.version, ecp.is_active, ecp.is_accept ");
		sql.append("FROM t_event_course_payment ecp ");
		sql.append("INNER JOIN t_payment_method pm ON ecp.id_payment_method = pm.id ");
		sql.append("INNER JOIN t_user u ON ecp.created_by = u.id ");
		sql.append("INNER JOIN t_profile p ON u.id = p.id_user ");
		sql.append("WHERE ecp.is_accept = :isAccept ");
		
		List<?> results = createNativeQuery(sql.toString())
				.setParameter("isAccept", isAccept)
				.setFirstResult(startPage)
                .setMaxResults(maxPage)
				.getResultList();
		List<GetAllEventCoursePaymentDtoDataRes> dataRes = new ArrayList<GetAllEventCoursePaymentDtoDataRes>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetAllEventCoursePaymentDtoDataRes reqData = new GetAllEventCoursePaymentDtoDataRes();
			reqData.setId(obj[0].toString());
			reqData.setIdFile((obj[1] != null)?obj[1].toString():null);
			reqData.setTotalprice(new BigInteger(obj[2].toString()));
			reqData.setPaymentName(obj[3].toString());
			reqData.setFullName(obj[4].toString());
			reqData.setPhoneNumber((obj[5]!=null)?obj[5].toString():null);
			reqData.setEmail(obj[6].toString());
			reqData.setCreatedAt(((Timestamp) obj[7]).toLocalDateTime());
			reqData.setVersion((Integer)obj[8]);
			reqData.setIsActive((Boolean)obj[9]);
			reqData.setIsAccept((obj[10] != null)?Boolean.valueOf(obj[10].toString()):null);
			
			dataRes.add(reqData);
		});
		
		return dataRes;
	}
	
	public Integer getCoungUnaccepted(Boolean isAccept) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(ecp.id) ");
		sql.append("FROM t_event_course_payment ecp ");
		sql.append("INNER JOIN t_payment_method pm ON ecp.id_payment_method = pm.id ");
		sql.append("INNER JOIN t_user u ON ecp.created_by = u.id ");
		sql.append("INNER JOIN t_profile p ON u.id = p.id_user ");
		sql.append("WHERE ecp.is_accept = :isAccept ");
		
		Object result = null;
		Integer res = 0;
		try {
			result = createNativeQuery(sql.toString())
					.setParameter("isAccept", isAccept)
					.getSingleResult();
			res = Integer.valueOf(result.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/*
	 * Report All Payment event course for admin
	 */
	public List<GetReportEventCoursePaymentDtoDataRes> getAllReportPaymentEventCourse(int startPage, int maxPage) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select tu.email , tp.full_name , tp.phone_number , tecp.total_price , tecp.invoice, tpm.payment_name ");
		sql.append("from t_event_course_payment tecp ");
		sql.append("left join t_user tu on tecp.created_by = tu.id ");
		sql.append("left join t_profile tp on tu.id = tp.id_user ");
		sql.append("left join t_payment_method tpm on tecp.id_payment_method  = tpm.id  where is_accept = true ");
		
		List<?> results = createNativeQuery(sql.toString())
				.setFirstResult(startPage)
                .setMaxResults(maxPage)
                .getResultList();
		List<GetReportEventCoursePaymentDtoDataRes> dataRes = new ArrayList<GetReportEventCoursePaymentDtoDataRes>();
		
		results.forEach(result -> {
			GetReportEventCoursePaymentDtoDataRes dataReq = new GetReportEventCoursePaymentDtoDataRes();
			Object[] obj = (Object[]) result;
			dataReq.setEmail(obj[0].toString());
			dataReq.setFullName(obj[1].toString());
			dataReq.setPhoneNumber((obj[2]!=null)?obj[2].toString():null);
			dataReq.setTotalPrice(Float.valueOf(obj[3].toString()));
			dataReq.setInvoice(obj[4].toString());
			dataReq.setPaymentName(obj[5].toString());
			
			dataRes.add(dataReq);
		});
		
		return dataRes;
	}
}
