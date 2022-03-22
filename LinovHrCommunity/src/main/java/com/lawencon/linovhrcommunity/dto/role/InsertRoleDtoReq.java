package com.lawencon.linovhrcommunity.dto.role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InsertRoleDtoReq {
	private String code;
	private String roleName;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
