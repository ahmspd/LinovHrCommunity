package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_industry", uniqueConstraints = {
		@UniqueConstraint(
					name = "industry_ck",
					columnNames = {"id", "code"}
				)
})
public class Industry extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "code", nullable = true, unique = true)
	private String code;

	@Column(name = "insudtry_name", nullable = true, length = 30)
	private String industryName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

}
