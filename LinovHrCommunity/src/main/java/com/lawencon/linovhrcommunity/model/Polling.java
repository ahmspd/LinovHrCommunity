package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_polling")
public class Polling extends BaseEntity {
	private static final long serialVersionUID = -9034083546988015483L;

	@OneToOne
	@JoinColumn(name = "id_thread", referencedColumnName = "id", nullable = false)
	private ThreadModel threadModel;

	@Column(name = "polling_name", length = 50, nullable = false)
	private String pollingName;

	public ThreadModel getThreadModel() {
		return threadModel;
	}

	public void setThreadModel(ThreadModel threadModel) {
		this.threadModel = threadModel;
	}

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}

}
