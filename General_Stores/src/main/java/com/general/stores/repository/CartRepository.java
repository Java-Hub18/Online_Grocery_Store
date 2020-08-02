package com.general.stores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.general.stores.entity.Cart;
import com.general.stores.entity.Customer;

public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query(value = "select c from Cart c where c.customer=?1")
	List<Cart> findCartItemsByCustomer(Customer customer);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "delete from Cart c where c.customer=?1 and c.id=?2")
	void deleteCartItems(Customer customer, Long id);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "delete from Cart c where c.id=?1")
	void deleteCartItem(Long id);
	
}
