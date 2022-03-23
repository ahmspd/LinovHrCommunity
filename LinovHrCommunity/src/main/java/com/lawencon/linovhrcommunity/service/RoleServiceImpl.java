package com.lawencon.linovhrcommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.linovhrcommunity.dao.RoleDao;
import com.lawencon.linovhrcommunity.dto.role.InsertRoleDtoReq;
import com.lawencon.linovhrcommunity.model.Role;

@Service
public class RoleServiceImpl extends BaseServiceLinovCommunityImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Role insert(InsertRoleDtoReq data) throws Exception {
		Role dataRole = new Role();
		try {
			begin();
			dataRole.setCode(data.getCode());
			System.out.println("tes => "+data.getCode());
			dataRole.setName(data.getRoleName());
			roleDao.save(dataRole);
			commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
		return dataRole;
	}
}
