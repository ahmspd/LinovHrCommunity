package com.lawencon.linovhrcommunity.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_polling_detail")
public class PollingDetail extends BaseEntity {
	private static final long serialVersionUID = -9034083546988015483L;

	@ManyToOne
	@JoinColumn(name = "id_polling", referencedColumnName = "id", nullable = false)
	private Polling polling;

	@Column(name = "polling_name", length = 50)
	private String pollingName;

	@OneToMany(mappedBy = "pollingDetail")
	private List<PollingDetailVote> pollingDetailVote;

	public Polling getPolling() {
		return polling;
	}

	public void setPolling(Polling polling) {
		this.polling = polling;
	}

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}

	public List<PollingDetailVote> getPollingDetailVote() {
		return pollingDetailVote;
	}

	public void setPollingDetailVote(List<PollingDetailVote> pollingDetailVote) {
		this.pollingDetailVote = pollingDetailVote;
	}

}
