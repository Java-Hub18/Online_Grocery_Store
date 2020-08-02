package com.general.stores.service.Impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.general.stores.entity.OrderDetail;
import com.general.stores.repository.OrderDetailRepository;
import com.general.stores.service.OrderDetailAdmService;

@Service
@Transactional
public class OrderDetailAdmServiceImpl implements OrderDetailAdmService 
{
	@Autowired
	OrderDetailRepository orderDetRepo;
	
	@Override
	public List<OrderDetail> getAllOrderDetail()
	{
		return orderDetRepo.findAll();
	}

	@Override
	public Optional<OrderDetail> getOrderDetailId(Long pid)
	{
		return orderDetRepo.findById(pid);
	}

	@Override
	public void deleteOrderDetail(Long pid) 
	{
		orderDetRepo.deleteById(pid);
	}

	@Override
	public List<OrderDetail> getOrdersByStatus(String orderStatus) {
		return orderDetRepo.findOrdersByStatus(orderStatus);
	}

	@Override
	public List<OrderDetail> getAllOrders() {
		return orderDetRepo.findAllOrders();
	}

	@Override
	public List<OrderDetail> getTop10Orders(String orderStatus, Pageable pageable) {
		return orderDetRepo.findTop10OrdersByStatus(orderStatus, pageable);
	}

}
