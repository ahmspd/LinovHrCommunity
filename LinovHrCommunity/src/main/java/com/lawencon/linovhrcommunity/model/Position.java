package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name="t_position", uniqueConstraints = {
		@UniqueConstraint(name = "position_bk", columnNames = "code"),
		@UniqueConstraint(
				name = "position_ck",
				columnNames = {"id", "code"}
		)
})
public class Position extends BaseEntity {
	private static final long serialVersionUID = -9034083546988015483L;

	@Column(length = 10, nullable = false)
	private String code;

	@Column(name = "position_name", length = 30, nullable = false)
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
