package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_order")
public class Order extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private User idUser;

	@Column(name = "is_accept")
	private Boolean isAccept;

	@ManyToOne
	@JoinColumn(name = "id_file")
	private File file;

	@ManyToOne
	@JoinColumn(name = "id_payment_method")
	private PaymentMethod idPaymentMethod;

	private String invoice;

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	public Boolean getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(Boolean isAccept) {
		this.isAccept = isAccept;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public PaymentMethod getIdPaymentMethod() {
		return idPaymentMethod;
	}

	public void setIdPaymentMethod(PaymentMethod idPaymentMethod) {
		this.idPaymentMethod = idPaymentMethod;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

}
