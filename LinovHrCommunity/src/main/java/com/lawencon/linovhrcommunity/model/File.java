package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_file")
public class File extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "t_file", nullable = true, length = 10)
	private String extensions;

	@Column(name = "content", nullable = true)
	private byte[] content;

	public String getExtensions() {
		return extensions;
	}

	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}
