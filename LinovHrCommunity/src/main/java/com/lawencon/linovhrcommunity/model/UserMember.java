package com.lawencon.linovhrcommunity.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_user_member")
public class UserMember extends BaseEntity{
	private static final long serialVersionUID = -5196455701225322056L;
	
	@ManyToOne
	@JoinColumn(name = "id_price_list", referencedColumnName = "id", nullable = false)
	private PriceList priceList;
	
	@Column(name = "date_end")
	private LocalDateTime dateEnd;

	public PriceList getPriceList() {
		return priceList;
	}

	public void setPriceList(PriceList priceList) {
		this.priceList = priceList;
	}

	public LocalDateTime getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDateTime dateEnd) {
		this.dateEnd = dateEnd;
	}
}
