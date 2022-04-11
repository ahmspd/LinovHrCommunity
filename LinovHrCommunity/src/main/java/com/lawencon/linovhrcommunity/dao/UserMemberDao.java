package com.lawencon.linovhrcommunity.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.dto.usermember.GetAllUserMemberDtoDataRes;
import com.lawencon.linovhrcommunity.model.EventCourse;
import com.lawencon.linovhrcommunity.model.Order;
import com.lawencon.linovhrcommunity.model.OrderDetail;
import com.lawencon.linovhrcommunity.model.UserMember;

@Repository
public class UserMemberDao extends BaseDaoImpl<UserMember> {

	public UserMember save(UserMember data) throws Exception {
		return super.save(data);
	}

	public UserMember findById(String id) throws Exception {
		return getById(id);
	}

	public List<UserMember> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

	public boolean updateDateEnd(Integer duration, String id, String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE t_user_member ");
		sql.append("SET date_end = now() + INTERVAL ");
		sql.append(String.format("'%s days', ", duration));
		sql.append("updated_by = :userId, ");
		sql.append("updated_at = now(), ");
		sql.append("version = version + 1 ");
		sql.append("WHERE id = :id ");

		int result = createNativeQuery(sql.toString())
				.setParameter("id", id)
//				.setParameter("duration", duration)
				.setParameter("userId", userId)
				.executeUpdate();

		return result > 0;
	}
	
	public OrderDetail getByUserMember(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t_order_detail.id, t_order_detail.id_event_course, t_order_detail.id_order, t_order_detail.id_user_member, t_order_detail.created_by, t_order_detail.created_at, t_order_detail.version, t_order_detail.is_active ");
		sql.append(" FROM t_order_detail ");
		sql.append(" LEFT JOIN t_user_member on t_user_member.id = t_order_detail.id_user_member ");
		sql.append(" LEFT JOIN t_order on t_order.id = t_order_detail.id_order ");
		sql.append(" LEFT JOIN t_user on t_user.id = t_order.id_user ");
		sql.append(" WHERE t_order_detail.id_user_member = :id ");
		
		Object result = createNativeQuery(sql.toString())
				.setParameter("id", id)
				.getSingleResult();
		
		Object[] obj = (Object[]) result;
		OrderDetail data = new OrderDetail();
		data.setId(String.valueOf(obj[0].toString()));
		
		EventCourse eventCourse = new EventCourse();
		eventCourse.setId(obj[1]!=null? String.valueOf(obj[1].toString()):null);
		data.setEventCourse(eventCourse);
		
		Order order = new Order();
		order.setId(String.valueOf(obj[2].toString()));
		data.setOrder(order);
		
		UserMember userMember = new UserMember();
		userMember.setId(String.valueOf(obj[3].toString()));
		
		data.setCreatedBy(String.valueOf(obj[4]).toString());
		data.setCreatedAt(((Timestamp)obj[5]).toLocalDateTime());
		data.setVersion(Integer.valueOf(obj[6].toString()));
		data.setIsActive(Boolean.valueOf(obj[7].toString()));
		
		return data;
	}
	
	public List<GetAllUserMemberDtoDataRes> getAllToAccept(Boolean isAccept, int startPage, int maxPage) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tum.id, tp.full_name, tu.email, tpm.payment_name, tor.is_accept, tor.id_file, tpl.price_name, tum.is_active ");
		sql.append(" FROM t_order_detail tod ");
		sql.append(" LEFT JOIN t_user_member tum on tum.id = tod.id_user_member ");
		sql.append(" LEFT JOIN t_order tor on tor.id = tod.id_order ");
		sql.append(" LEFT JOIN t_user tu on tu.id = tor.id_user ");
		sql.append(" LEFT JOIN t_profile tp on tu.id = tp.id_user ");
		sql.append(" LEFT JOIN t_payment_method tpm on tpm.id = tor.id_payment_method ");
		sql.append(" LEFT JOIN t_file tf on tf.id = tor.id_file ");
		sql.append(" LEFT JOIN t_price_list tpl on tpl.id = tum.id_price_list ");
		sql.append(" WHERE tor.is_accept = :isAccept and tod.id_event_course is null");
		
		List<?> results = createNativeQuery(sql.toString())
				.setParameter("isAccept", isAccept)
				.setFirstResult(startPage)
                .setMaxResults(maxPage)
                .getResultList();
		
		List<GetAllUserMemberDtoDataRes> dataRes = new ArrayList<GetAllUserMemberDtoDataRes>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetAllUserMemberDtoDataRes data = new GetAllUserMemberDtoDataRes();
			data.setId((obj[0]!=null)?obj[0].toString():null);
			data.setFullName((obj[1]!=null)?String.valueOf(obj[1]).toString():null);
			data.setEmail((obj[2]!=null)?String.valueOf(obj[2]).toString():null);
			data.setPaymentName((obj[3]!=null)?String.valueOf(obj[3]).toString():null);
			data.setIsAccept(obj[4]!=null? Boolean.valueOf(obj[4].toString()):null);
			data.setIdFile((obj[5]!=null)?String.valueOf(obj[5]).toString():null);
			data.setPriceName((obj[6]!=null)?String.valueOf(obj[6]).toString():null);
			data.setIsActive((obj[7]!=null)?Boolean.valueOf(obj[7].toString()):null);
					
			dataRes.add(data);
		});
		
		return dataRes;
	}
	
	public Integer getCountToAccept(Boolean isAccept) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(tum.id) ");
		sql.append(" FROM t_order_detail tod ");
		sql.append(" LEFT JOIN t_user_member tum on tum.id = tod.id_user_member ");
		sql.append(" LEFT JOIN t_order tor on tor.id = tod.id_order ");
		sql.append(" LEFT JOIN t_user tu on tu.id = tor.id_user ");
		sql.append(" LEFT JOIN t_profile tp on tu.id = tp.id_user ");
		sql.append(" LEFT JOIN t_payment_method tpm on tpm.id = tor.id_payment_method ");
		sql.append(" LEFT JOIN t_file tf on tf.id = tor.id_file ");
		sql.append(" LEFT JOIN t_price_list tpl on tpl.id = tum.id_price_list ");
		sql.append(" WHERE tor.is_accept = :isAccept and tod.id_event_course is null");
		
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

}