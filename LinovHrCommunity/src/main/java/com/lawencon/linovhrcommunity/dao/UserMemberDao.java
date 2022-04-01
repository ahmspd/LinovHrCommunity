package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
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

	public boolean updateDateEnd(String duration, String id, String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE t_user_member ");
		sql.append("SET date_end = INTERVAL now() + :duration, ");
		sql.append("update_by = :userId, ");
		sql.append("update_at = now(), ");
		sql.append("version = version + 1 ");
		sql.append("WHERE id = :id ");

		int result = createNativeQuery(sql.toString()).setParameter("id", id).setParameter("duration", duration)
				.setParameter("userId", userId).executeUpdate();

		return result > 0;
	}

}