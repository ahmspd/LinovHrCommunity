package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table
public class City extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "code", length = 7, unique = true, nullable = true)
	private String code;

	@Column(name = "city_name", length = 100, nullable = true)
	private String cityName;

	@ManyToOne
	@JoinColumn(name = "code_province", nullable = true)
	private String codeProvince;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCodeProvince() {
		return codeProvince;
	}

	public void setCodeProvince(String codeProvince) {
		this.codeProvince = codeProvince;
	}

}
