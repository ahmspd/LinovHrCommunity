package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_thread")
public class ThreadModel extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(nullable = false, columnDefinition = "text")
	private String contents;
	
	@OneToOne
	@JoinColumn(name = "id_file")
	private File file;
	
	@OneToOne
	@JoinColumn(name = "id_thread_type")
	private ThreadType threadType;
	
	@Column(name = "is_premium")
	private Boolean isPremium;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public ThreadType getThreadType() {
		return threadType;
	}

	public void setThreadType(ThreadType threadType) {
		this.threadType = threadType;
	}

	public Boolean getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}
}	
