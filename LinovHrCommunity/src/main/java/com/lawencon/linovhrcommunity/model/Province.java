package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_province", uniqueConstraints = @UniqueConstraint(name = "province_bk", columnNames = "code"))
public class Province extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(length = 7, nullable = false)
	private String code;
	
	@Column(length = 50, nullable = false)
	private String provinceName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}
