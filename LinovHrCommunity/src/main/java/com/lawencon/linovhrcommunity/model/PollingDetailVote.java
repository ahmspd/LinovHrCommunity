package com.lawencon.linovhrcommunity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_polling_detail_vote")
public class PollingDetailVote extends BaseEntity {
	private static final long serialVersionUID = -9034083546988015483L;

	@ManyToOne
	@JoinColumn(name = "id_polling_detail", nullable = false)
	private PollingDetail pollingDetail;

	public PollingDetail getPollingDetail() {
		return pollingDetail;
	}

	public void setPollingDetail(PollingDetail pollingDetail) {
		this.pollingDetail = pollingDetail;
	}
	
}
