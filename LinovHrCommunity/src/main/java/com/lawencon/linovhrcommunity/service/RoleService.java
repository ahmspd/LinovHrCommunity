package com.lawencon.linovhrcommunity.service;

import com.lawencon.linovhrcommunity.dto.role.InsertRoleDtoReq;
import com.lawencon.linovhrcommunity.model.Role;

public interface RoleService {
	Role insert(InsertRoleDtoReq data) throws Exception;
}
