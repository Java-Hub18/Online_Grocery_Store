package com.general.stores.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import com.general.stores.entity.Order;

public interface OrderService {
	boolean saveProductOrder(Order order);
	void deleteOrdersByNum(int orderNum);
	List<Order> getAllOrdersByCustomer(String customerEmail);
	Set<Long> getOrderIdByEmail(String customerEmail);
	boolean saveCartOrder(int orderNum, String customerName, String customerEmail, String customerPhone,
	String customerAddress, String customerAddressType, double amount, boolean active, Date orderDate);
	Long getOrderIdByNum(int orderNum);
	void saveOrders(List<Order> orders);
	List<Order> getLastOrderByIdDesc(Pageable pageable);
	Long getOrdersCount();
}
