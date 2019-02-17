package com.softedge.urbantrend.customerservice.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long customerId;
	@Column(name = "customer_name")
	private String customerName;
	@Column(name = "customer_password")
	private String customerPassword;
	@Column(name = "customer_gender")
	private String gender;
	@ElementCollection
	@CollectionTable(name = "customer_addresses")
	private List<Address> customerAddress;
	@Column(name = "customer_contact", unique = true)
	private String customerContact;
	@Column(name = "customer_email", unique = true)
	private String customerEmail;
	@Column(name = "customer_status")
	@Enumerated(EnumType.STRING)
	private CustomerStatus customerStatus;

	public enum CustomerStatus {
		ACTIVE, INACTIVE
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Address> getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(List<Address> customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public CustomerStatus getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(CustomerStatus customerStatus) {
		this.customerStatus = customerStatus;
	}

}
