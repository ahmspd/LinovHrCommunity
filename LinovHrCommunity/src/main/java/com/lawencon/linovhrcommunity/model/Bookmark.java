package com.lawencon.linovhrcommunity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_bookmark")
public class Bookmark extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@ManyToOne
	@JoinColumn(name = "id_thread", nullable = true, unique = true)
	private ThreadModel thread;

	public ThreadModel getThread() {
		return thread;
	}

	public void setThread(ThreadModel thread) {
		this.thread = thread;
	}

	

}
