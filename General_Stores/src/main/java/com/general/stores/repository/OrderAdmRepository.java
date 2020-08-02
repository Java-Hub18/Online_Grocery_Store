package com.general.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.general.stores.entity.Order;

public interface OrderAdmRepository extends JpaRepository<Order, Long>
{
	@Modifying
	@Query(value = "update OrderDetail o set o.orderStatus =:orderStatus where o.id =:id")
	void updateOrderStatusById(@Param("orderStatus") String orderStatus, @Param("id") Long id);
	
	@Modifying
	@Query(value = "delete from Order o where o.id =:id")
	void deleteOrdersById(@Param("id") Long id);
	
	@Modifying
	@Query(value = "delete from OrderDetail o where o.order =:orderId")
	void deleteOrderDetailById(@Param("orderId") Order orderId);
}
