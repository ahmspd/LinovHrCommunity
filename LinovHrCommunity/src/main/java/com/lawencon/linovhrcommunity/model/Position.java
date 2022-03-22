package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(
				name = "position_ck",
				columnNames = {"id", "code"}
		)
}, name="t_position")
public class Position extends BaseEntity {
	private static final long serialVersionUID = -9034083546988015483L;

	@Column(nullable = false)
	private String code;

	@Column(name = "position_name", nullable = false)
	private String positionName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
