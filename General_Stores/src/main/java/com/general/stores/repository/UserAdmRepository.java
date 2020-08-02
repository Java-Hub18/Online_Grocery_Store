package com.general.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.general.stores.entity.Customer;

public interface UserAdmRepository extends JpaRepository<Customer,Long>
{

}
