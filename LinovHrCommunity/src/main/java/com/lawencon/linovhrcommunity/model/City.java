package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_city", uniqueConstraints = @UniqueConstraint(name = "city_bk", columnNames = "code"))
public class City extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "code", length = 7, nullable = false)
	private String code;

	@Column(name = "city_name", length = 100, nullable = false)
	private String cityName;

	@OneToOne
	@JoinColumn(name = "code_province", referencedColumnName = "code", nullable = false)
	private Province codeProvince;

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

	public Province getCodeProvince() {
		return codeProvince;
	}

	public void setCodeProvince(Province codeProvince) {
		this.codeProvince = codeProvince;
	}

}
