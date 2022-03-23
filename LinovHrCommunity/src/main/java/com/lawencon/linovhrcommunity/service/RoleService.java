package com.lawencon.linovhrcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.linovhrcommunity.dao.RoleDao;
import com.lawencon.linovhrcommunity.dto.role.DeleteByIdRoleRes;
import com.lawencon.linovhrcommunity.dto.role.GetAllRoleDtoDataRes;
import com.lawencon.linovhrcommunity.dto.role.GetAllRoleDtoRes;
import com.lawencon.linovhrcommunity.dto.role.GetByIdRoleDtoDataRes;
import com.lawencon.linovhrcommunity.dto.role.GetByIdRoleDtoRes;
import com.lawencon.linovhrcommunity.dto.role.InsertRoleDtoDataRes;
import com.lawencon.linovhrcommunity.dto.role.InsertRoleDtoReq;
import com.lawencon.linovhrcommunity.dto.role.InsertRoleDtoRes;
import com.lawencon.linovhrcommunity.dto.role.UpdateRoleDtoDataRes;
import com.lawencon.linovhrcommunity.dto.role.UpdateRoleDtoReq;
import com.lawencon.linovhrcommunity.dto.role.UpdateRoleDtoRes;
import com.lawencon.linovhrcommunity.model.Role;

@Service
public class RoleService extends BaseServiceLinovCommunityImpl {

	private RoleDao roleDao;
	
	@Autowired
	public RoleService(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public InsertRoleDtoRes insert(InsertRoleDtoReq dataReq) throws Exception {
		try {
			Role newRole = new Role();
			String roleCode = dataReq.getCode();
			String roleName = dataReq.getName();
			newRole.setCode(roleCode);
			newRole.setName(roleName);
			newRole.setCreatedBy("1");
			
			begin();
			newRole = roleDao.save(newRole);			
			commit();
			
			InsertRoleDtoDataRes dataRes = new InsertRoleDtoDataRes();
			dataRes.setId(newRole.getId());
			
			InsertRoleDtoRes insertRes = new InsertRoleDtoRes();
			insertRes.setData(dataRes);
			insertRes.setMessage("Success Insert New Role !");
			
			return insertRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public UpdateRoleDtoRes update(UpdateRoleDtoReq dataReq) throws Exception {
		try {
			Role editRole = roleDao.findById(dataReq.getId());
			String roleCode = dataReq.getCode();
			String roleName = dataReq.getName();
			editRole.setCode(roleCode);
			editRole.setName(roleName);
			editRole.setUpdatedBy("1");
			editRole.setVersion(dataReq.getVersion());
			
			begin();
			editRole = roleDao.save(editRole);			
			commit();
			
			UpdateRoleDtoDataRes dataRes = new UpdateRoleDtoDataRes();
			dataRes.setVersion(editRole.getVersion());
			
			UpdateRoleDtoRes updateRes = new UpdateRoleDtoRes();
			updateRes.setData(dataRes);
			updateRes.setMessage("Success Update Role !");
			
			return updateRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

	public GetByIdRoleDtoRes findById(String id) throws Exception {
		Role roleById = roleDao.findById(id);
		
		GetByIdRoleDtoDataRes dataById = new GetByIdRoleDtoDataRes();
		dataById.setId(roleById.getId());
		dataById.setCode(roleById.getCode());
		dataById.setName(roleById.getName());
		dataById.setIsActive(roleById.getIsActive());
		dataById.setVersion(roleById.getVersion());
		
		GetByIdRoleDtoRes findByIdRes = new GetByIdRoleDtoRes();
		findByIdRes.setData(dataById);
		
		return findByIdRes;
	}

	public GetAllRoleDtoRes findAll() throws Exception {
		List<Role> listRoles = new ArrayList<>();
		listRoles = roleDao.findAll();
		
		List<GetAllRoleDtoDataRes> dataAll = new ArrayList<>();

		listRoles.forEach(role -> {
			GetAllRoleDtoDataRes data = new GetAllRoleDtoDataRes();
			data.setId(role.getId());
			data.setCode(role.getCode());
			data.setName(role.getName());
			data.setIsActive(role.getIsActive());
			data.setVersion(role.getVersion());
			
			dataAll.add(data);
		});
		
		GetAllRoleDtoRes findAllRes = new GetAllRoleDtoRes();
		findAllRes.setData(dataAll);

		return findAllRes;
	}

	public DeleteByIdRoleRes deleteById(String id) throws Exception {
		DeleteByIdRoleRes delRes = new DeleteByIdRoleRes();
		try {
			begin();
			boolean isDeleted = roleDao.deleteById(id);
			commit();
			
			if(isDeleted) {
				delRes.setMessage("Delete Success !");
			}else {
				throw new Exception("Delete Failed !");
			}
			
			return delRes;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

}
