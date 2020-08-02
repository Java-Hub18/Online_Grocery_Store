package com.general.stores.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@NotNull(message = "Customer name is required.")
	@Size(min = 2, max = 50, message = "Customer name must be between 2 and 50 characters.")
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull(message = "Customer password is required.")
	@Size(min = 6, max = 60, message = "Password must be between 6 and 60 characters.")
	@Column(name = "password", nullable = false)
    private String password;
	
	@NotNull(message = "Customer email is required.")
	@Size(min = 11, max = 100, message = "Email must be between 11 and 100 characters.")
	@Column(name = "email", nullable = false, unique = true)
    private String email;
	
	@NotNull(message = "Customer gender is required.")
	@Size(min = 4, max = 6, message = "Gender is required.")
	@Column(name = "gender", nullable = false)
	private String gender;
	
	@NotNull(message = "Customer phone is required.")
	@Size(max = 10, message = "Phone Number must be of 10 digits only.")
	@Column(name = "phone", nullable = false, unique = true)
    private String phone; 
	
	@NotNull(message = "Customer address is required.")
	@Column(name = "address", nullable = false)
    private String address;
	
	@NotNull(message = "Pin Code is required.")
	@Column(name = "pin_code", nullable = false)
    private String pinCode;
		
	@Column(name = "valid", nullable = false)
    private Boolean valid;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "added_date", nullable = false)
    private Date createDate;
    
	public Customer() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", gender="
				+ gender + ", phone=" + phone + ", address=" + address + ", pinCode=" + pinCode + ", valid=" + valid
				+ ", createDate=" + createDate + "]";
	} 
    
}
