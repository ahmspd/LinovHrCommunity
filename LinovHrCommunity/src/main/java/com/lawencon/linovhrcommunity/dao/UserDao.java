package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.dto.user.LoginUserDtoDataRes;
import com.lawencon.linovhrcommunity.model.Role;
import com.lawencon.linovhrcommunity.model.User;

@Repository
public class UserDao extends BaseDaoImpl<User> {

	public User findById(String id) throws Exception {
		return getById(id);
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

	public List<User> findAll() throws Exception {
		return getAll();
	}

	public User save(User entity) throws Exception {
		return super.save(entity);
	}
	
	public LoginUserDtoDataRes getUserByEmail(String email) throws Exception {
		StringBuilder sql=new StringBuilder();  
		sql.append("select ts.id, email, password, ts.is_member , tr.id as id_role, tr.code , tr.role_name, ");
		sql.append("tp.id as id_profile, tp.full_name , tp.phone_number, tp.id_file ");
		sql.append("from t_user ts left join t_role tr on ts.id_role = tr.id ");
		sql.append("left join t_profile tp on tp.id_user = ts.id where email = :email");
		Object result = null;
		Object[] obj = null;
		LoginUserDtoDataRes userData = new LoginUserDtoDataRes();
		try {
			result = createNativeQuery(String.valueOf(sql)).setParameter("email", email).getSingleResult();
			obj = (Object[]) result;
			
			userData.setId(obj[0].toString());
			userData.setEmail(obj[1].toString());
			userData.setPassword(obj[2].toString());
			userData.setIsMember(obj[3]!=null? Boolean.valueOf(obj[3].toString()):false);
			userData.setIdRole(obj[4].toString());
			userData.setRoleCode(obj[5].toString());
			userData.setRoleName(obj[6].toString());
			userData.setIdProfile(obj[7].toString());
			userData.setFullName(obj[8].toString());
			userData.setPhoneNumber(obj[9].toString());
			userData.setIdFile(obj[10]!=null? obj[10].toString():null);
		}
		catch(NoResultException e) {
			e.printStackTrace();
		}
		return userData;
	}
}
