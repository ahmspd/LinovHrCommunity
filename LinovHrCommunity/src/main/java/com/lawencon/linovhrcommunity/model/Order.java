package com.lawencon.linovhrcommunity.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_order")
public class Order extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;

	@ManyToOne
	@JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
	private User idUser;

	@Column(name = "is_accept")
	private Boolean isAccept;

	@OneToOne
	@JoinColumn(name = "id_file", referencedColumnName = "id")
	private File file;

	@OneToOne
	@JoinColumn(name = "id_payment_method", referencedColumnName = "id")
	private PaymentMethod idPaymentMethod;

	@Column(length = 30)
	private String invoice;

	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetail;

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

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

}
