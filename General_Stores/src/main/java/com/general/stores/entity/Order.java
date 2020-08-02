package com.general.stores.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "orders", uniqueConstraints = { @UniqueConstraint(columnNames = "order_num") })
public class Order {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "order_num", nullable = false, unique = true)
	private int orderNum;
	
	@Column(name = "amount", nullable = false)
    private double amount;
    
    @Column(name = "customer_name", length = 30, nullable = false)
    private String customerName;
    
    @Column(name = "customer_address", length = 255, nullable = false)
    private String customerAddress;
    
    @Column(name = "customer_address_type", length = 15, nullable = false)
    private String customerAddressType;
    
    @Column(name = "customer_email", length = 50, nullable = false)
    private String customerEmail;
    
    @Column(name = "customer_phone", length = 10, nullable = false)
    private String customerPhone;
    
    @Column(name = "pin_code", length = 10, nullable = false)
    private String pinCode;
    
    @Column(name = "active", nullable = false)
	private Boolean active;
    
//    @Column(name = "payment_id", length = 6, nullable = false)
//    private int paymentId;
    
    @Column(name = "order_date", nullable = false)
    private Date orderDate;
    
    //@OneToMany(mappedBy = "order")
    //@OneToMany(cascade = CascadeType.ALL, mappedBy="order", orphanRemoval = true)
    //private Set<OrderDetail> odSet = new HashSet<>();

    
    
	public Order() {}
	
	public Order(int orderNum, double amount, String customerName, String customerAddress, String customerAddressType,
			String customerEmail, String customerPhone, String pinCode, Boolean active, Date orderDate) {
		this.orderNum = orderNum;
		this.amount = amount;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerAddressType = customerAddressType;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
		this.pinCode = pinCode;
		this.active = active;
		this.orderDate = orderDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerAddressType() {
		return customerAddressType;
	}

	public void setCustomerAddressType(String customerAddressType) {
		this.customerAddressType = customerAddressType;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNum=" + orderNum + ", amount=" + amount + ", customerName=" + customerName
				+ ", customerAddress=" + customerAddress + ", customerAddressType=" + customerAddressType
				+ ", customerEmail=" + customerEmail + ", customerPhone=" + customerPhone + ", pinCode=" + pinCode
				+ ", active=" + active + ", orderDate=" + orderDate + "]";
	}
    
}
