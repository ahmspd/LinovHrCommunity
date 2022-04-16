package com.lawencon.linovhrcommunity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_like", uniqueConstraints = @UniqueConstraint(name = "like_ck", columnNames = { "id", "id_thread" }))
public class Like extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;

	@OneToOne
	@JoinColumn(name = "id_thread", referencedColumnName = "id", nullable = false)
	private ThreadModel idThread;

	public ThreadModel getIdThread() {
		return idThread;
	}

	public void setIdThread(ThreadModel idThread) {
		this.idThread = idThread;
	}

}