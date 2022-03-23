package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_role")
public class Role extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(name = "code", length = 10, nullable = false)
	private String code;

	@Column(name = "role_name", length = 30, nullable = false)
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
