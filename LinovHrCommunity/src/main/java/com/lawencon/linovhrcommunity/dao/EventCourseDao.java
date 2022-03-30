package com.lawencon.linovhrcommunity.dao;

import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.dto.eventcourse.GetAllEventCourseDtoDataRes;
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

	public List<GetAllEventCourseDtoDataRes> getAllActive(String type) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ec.id AS ec_id, ec.contents AS ec_contents, ec.title, ec.event_course_location, ec.price, ec.date_start, ec.date_end, ec.time_start, ec.time_end, ec.id_file AS f_id, f.extensions, f.contents AS f_contents, ec.created_by AS u_id, p.full_name, ec.created_at, ec.version, ec.is_active ");
		sql.append("FROM t_event_course ec ");
		sql.append("INNER JOIN t_event_course_type ect ON ec.id_event_course_type = ect.id ");
		sql.append("INNER JOIN t_file f ON ec.id_file = f.id ");
		sql.append("INNER JOIN t_user u ON ec.created_by = u.id ");
		sql.append("INNER JOIN t_profile p ON u.id = p.id_user ");
		sql.append("WHERE ect.event_course_type_name = :type AND ec.is_active = true;");

		List<?> results = createNativeQuery(sql.toString()).setParameter("type", type).getResultList();
		List<GetAllEventCourseDtoDataRes> dataRes = new ArrayList<GetAllEventCourseDtoDataRes>();

		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetAllEventCourseDtoDataRes reqData = new GetAllEventCourseDtoDataRes();
			reqData.setId(obj[0].toString());
			reqData.setContents(obj[1].toString());
			reqData.setTitle(obj[2].toString());
			reqData.setEventCourseLocation(obj[3].toString());
			reqData.setPrice(new BigInteger(obj[4].toString()));
			reqData.setDateStart((Date)obj[5]);
			reqData.setDateEnd((Date)obj[6]);
			reqData.setTimeStart((Time)obj[7]);
			reqData.setTimeEnd((Time)obj[8]);
			reqData.setIdFile(obj[9].toString());
			reqData.setFileExtensions(obj[10].toString());
			reqData.setFileContents((byte[])obj[11]);
			reqData.setCreatedBy(obj[12].toString());
			reqData.setFullName(obj[13].toString());
			reqData.setCreatedAt(((Timestamp) obj[14]).toLocalDateTime());
			reqData.setVersion((Integer)obj[15]);
			reqData.setIsActive((Boolean)obj[16]);
			
			dataRes.add(reqData);
		});

		return dataRes;

	}
}






