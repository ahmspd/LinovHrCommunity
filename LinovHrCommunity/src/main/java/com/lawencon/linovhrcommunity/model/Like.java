package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_like")
public class Like extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;

	@ManyToOne
	@JoinColumn(name = "id_thread", nullable = false)
	private ThreadModel idThread;

	public ThreadModel getIdThread() {
		return idThread;
	}

	public void setIdThread(ThreadModel idThread) {
		this.idThread = idThread;
	}

}