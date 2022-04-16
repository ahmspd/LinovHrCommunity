package com.lawencon.linovhrcommunity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_bookmark", uniqueConstraints = @UniqueConstraint(name="bookmark_ck", columnNames = { "id", "id_thread" }))
public class Bookmark extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@OneToOne
	@JoinColumn(name = "id_thread", referencedColumnName = "id", nullable = false)
	private ThreadModel thread;

	public ThreadModel getThread() {
		return thread;
	}

	public void setThread(ThreadModel thread) {
		this.thread = thread;
	}

}
