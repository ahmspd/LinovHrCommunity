package com.lawencon.linovhrcommunity.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_event_course_payment", uniqueConstraints = @UniqueConstraint(name = "file_bk", columnNames = "id_file"))
public class EventCoursePayment extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@OneToOne
	@JoinColumn(name = "id_payment_method", referencedColumnName = "id")
	private PaymentMethod paymentMethod;

	@Column(name = "is_accept")
	private Boolean isAccept;

	@OneToOne
	@JoinColumn(name = "id_file", referencedColumnName = "id")
	private File file;

	@Column(name = "invoice", length = 30)
	private String invoice;

	@Column(name = "total_price", nullable = false)
	private BigInteger totalPrice;

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
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

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public BigInteger getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigInteger totalPrice) {
		this.totalPrice = totalPrice;
	}

}
