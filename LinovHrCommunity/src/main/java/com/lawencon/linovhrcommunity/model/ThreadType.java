package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_thread_type", uniqueConstraints = @UniqueConstraint(name = "thread_type_bk", columnNames = "code"))
public class ThreadType extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(length = 10, nullable = false)
	private String code;
	
	@Column(name = "thread_type_name", length = 30, nullable = false)
	private String threadTypeName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getThreadTypeName() {
		return threadTypeName;
	}

	public void setThreadTypeName(String threadTypeName) {
		this.threadTypeName = threadTypeName;
	}
}
