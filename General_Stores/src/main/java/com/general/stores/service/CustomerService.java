package com.general.stores.service;

import com.general.stores.entity.Customer;

public interface CustomerService {

	void saveCustomer(Customer customer);	
	boolean loginCustomer(String email, String password);
	Customer findCustomerByEmail(String email);
	String findCustomerPassword(String email);
	void updateCustomerById(Long id, String name, String address, String gender, String phone, String pinCode);
	void sendMail(Customer user);
	void updatePassword(String email, String password);
	Customer findCustomerByPhone(String phone);
	Long getCustomerId(String customerEmail);
}
