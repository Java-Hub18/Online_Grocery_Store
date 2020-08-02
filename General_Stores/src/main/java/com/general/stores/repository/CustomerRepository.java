package com.general.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.general.stores.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query(value = "select c from Customer c where c.phone=?1")
	Customer checkCustomerPhone(String phone);
	
	@Query(value = "select c from Customer c where c.email=?1")
	Customer checkCustomerByEmail(String email);
	
	@Query(value = "select c.password from Customer c where c.email=?1")
	String getCustomerPasswordByEmail(String email);
	
	@Query(value = "select c.valid from Customer c where c.email=?1 and c.password=?2")
	boolean findValidCustomer(String email, String password);
	
	@Modifying(clearAutomatically = true)
	@Query("update Customer c set c.password =:password where c.email =:email")
	void changePassword(@Param("email") String email, @Param("password") String password);
	
	@Query(value = "select c.id from Customer c where c.email=?1")
	Long findCustomerId(String customerEmail);
	
	@Modifying(clearAutomatically = true)
	@Query("update Customer c set c.name =:name,c.address =:address,c.gender = :gender,c.phone =:phone,c.pinCode =:pinCode where c.id =:id")
	void updateMyCustomer(@Param("id") Long id,@Param("name") String name, @Param("address") String address,@Param("gender") String gender, 
			@Param("phone") String phone, @Param("pinCode") String pinCode);
	
	
	
}
