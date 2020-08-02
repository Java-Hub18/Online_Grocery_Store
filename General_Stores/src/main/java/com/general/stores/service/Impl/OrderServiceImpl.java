package com.general.stores.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.general.stores.entity.Order;
import com.general.stores.repository.OrderRepository;
import com.general.stores.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public boolean saveProductOrder(Order order) {
		boolean status = false;
		if (order != null) {
			status = true;
			orderRepository.save(order);
		}
		return status;
	}

	@Override
	public List<Order> getAllOrdersByCustomer(String customerEmail) {
		return orderRepository.findAllOrdersByCustomer(customerEmail);
	}

	@Override
	public void deleteOrdersByNum(int orderNum) {
		orderRepository.removeOrdersByNum(orderNum);	
	}

	@Override
	public Set<Long> getOrderIdByEmail(String customerEmail) {
		// TODO Auto-generated method stub
		return orderRepository.findOrderIdByEmail(customerEmail);
	}

	@Override
	public boolean saveCartOrder(int orderNum, String customerName, String customerEmail, String customerPhone,
			String customerAddress, String customerAddressType, double amount, boolean active, Date orderDate) {
		boolean status = false;
		if (orderNum != 0) {
			status = true;
			orderRepository.saveOrders(orderNum, customerName, customerEmail, customerPhone, customerAddress, customerAddressType, amount, active, orderDate);		}
		return status;
	}

	@Override
	public Long getOrderIdByNum(int orderNum) {
		return orderRepository.findOrderIdByNum(orderNum);
	}

	@Override
	public void saveOrders(List<Order> orders) {
		orderRepository.saveAll(orders);
		
	}

	@Override
	public List<Order> getLastOrderByIdDesc(Pageable pageable) {
		return orderRepository.getLastOrder(pageable);
	}

	@Override
	public Long getOrdersCount() {
		return orderRepository.count();
	}

}
