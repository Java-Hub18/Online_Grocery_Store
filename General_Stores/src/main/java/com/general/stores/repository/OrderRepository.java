package com.general.stores.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.general.stores.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "select o from Order o where o.customerEmail=?1")
	List<Order> findAllOrdersByCustomer(String customerEmail);
	
	@Query(value = "select o.id from Order o where o.customerEmail=?1")
	Set<Long> findOrderIdByEmail(String customerEmail);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "delete from Order o where o.orderNum =:orderNum")
	void removeOrdersByNum(@Param("orderNum") int orderNum);
	
	@Modifying
	@Query(value = "insert into orders (order_num, customer_name, customer_email, customer_phone, customer_address, customer_address_type, amount, active, order_date) VALUES (:orderNum, :customerName, :customerEmail, :customerPhone, :customerAddress, :customerAddressType, :amount, :active, :orderDate)", nativeQuery = true)
	void saveOrders(@Param("orderNum") int orderNum, @Param("customerName") String customerName,@Param("customerEmail") String customerEmail,@Param("customerPhone") String customerPhone,@Param("customerAddress") String customerAddress,@Param("customerAddressType") String customerAddressType, @Param("amount") double amount,
	@Param("active") boolean active, @Param("orderDate") Date orderDate);
	
	@Query(value = "select o from Order o order by o.id desc")
	List<Order> getLastOrder(Pageable pageable);
	
	@Query(value = "select o.id from Order o where o.orderNum=?1")
	Long findOrderIdByNum(int orderNum);
}
