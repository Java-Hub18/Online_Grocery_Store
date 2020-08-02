package com.general.stores.service.Impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.general.stores.entity.Order;
import com.general.stores.repository.OrderAdmRepository;
import com.general.stores.service.OrderAdmService;

@Service
@Transactional
public class OrderAdmServiceImpl implements OrderAdmService
{
	
	@Autowired
	OrderAdmRepository orderAdmRepo;
	
	@Override
	public Optional<Order> getOrderId(Long pid) 
	{
		
		return orderAdmRepo.findById(pid);
	}
	
	@Override
	public void deleteOrdersById(Long id)
	{
		orderAdmRepo.deleteOrdersById(id);
	}

	@Override
	public void deleteAll(List<Order> ids)
	{
		
	}

	@Override
	public void changeOrderStatus(String orderStatus, Long id) {
		orderAdmRepo.updateOrderStatusById(orderStatus, id);
		
	}

	@Override
	public void deleteOrderDetailById(Order orderId) {
		orderAdmRepo.deleteOrderDetailById(orderId);	
	}

}
