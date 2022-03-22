package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_polling")
public class Polling extends BaseEntity {
	private static final long serialVersionUID = -9034083546988015483L;

	@ManyToOne
	@JoinColumn(name = "id_thread", nullable = false)
	private ThreadModel idThread;

	@Column(name = "polling_name", nullable = false)
	private String pollingName;

	public ThreadModel getIdThread() {
		return idThread;
	}

	public void setIdThread(ThreadModel idThread) {
		this.idThread = idThread;
	}

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}

}
