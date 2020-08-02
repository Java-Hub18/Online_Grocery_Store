package com.general.stores.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "ORDER_DETAIL_ORD_FK"))
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "ORDER_DETAIL_PROD_FK"))
	private Product product;

	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@Column(name = "mrp_price", nullable = false)
	private double mrpPrice;

	@Column(name = "price", nullable = false)
	private double price;

	@Column(name = "amount", nullable = false)
	private double amount;
	
	@Column(name = "payment_id", length = 6, nullable = false)
    private int paymentId;
	
	@Column(name = "order_status", length = 25, nullable = false)
	private String orderStatus;
	
	@Column(name = "payment_mode", length = 25, nullable = false)
	private String paymentMode;

	public OrderDetail() {
	}
	
	

	public OrderDetail(Order order, Product product, int quantity, double mrpPrice, double price, double amount,
			int paymentId, String orderStatus, String paymentMode) {
		super();
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.mrpPrice = mrpPrice;
		this.price = price;
		this.amount = amount;
		this.paymentId = paymentId;
		this.orderStatus = orderStatus;
		this.paymentMode = paymentMode;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getMrpPrice() {
		return mrpPrice;
	}

	public void setMrpPrice(double mrpPrice) {
		this.mrpPrice = mrpPrice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", order=" + order + ", product=" + product + ", quantity=" + quantity
				+ ", mrpPrice=" + mrpPrice + ", price=" + price + ", amount=" + amount + ", paymentId=" + paymentId
				+ ", orderStatus=" + orderStatus + ", paymentMode=" + paymentMode + "]";
	}
	
}
