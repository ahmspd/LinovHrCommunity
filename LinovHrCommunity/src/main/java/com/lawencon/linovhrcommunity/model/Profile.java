package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_profile")
public class Profile extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;

	@OneToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	@Column(name = "full_name", length = 50, nullable = false)
	private String fullName;
	
	@Column(name = "phone_number", length = 15, nullable = true)
	private String phoneNumber;
	
	@Column(name = "postal_code", length = 10, nullable = true)
	private String postalCode;
	
	@Column(name = "company", length = 30, nullable = false)
	private String company;
	
	@OneToOne
	@JoinColumn(name = "id_industry", nullable = false)
	private Industry industry;
	
	@OneToOne
	@JoinColumn(name = "id_position", nullable = false)
	private Position position;
	
	@ManyToOne
	@JoinColumn(name = "id_province" )
	private Province province;
	
	@ManyToOne
	@JoinColumn(name = "id_city")
	private City city;
	
	@OneToOne
	@JoinColumn(name = "id_file")
	private File file;
	
	@Column(name = "instagram")
	private String instagram;
	
	@Column(name = "twitter")
	private String twitter;
	
	@Column(name = "facebook")
	private String facebook;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
