package com.general.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.general.stores.entity.Contact;

public interface ContactAdmRepository extends JpaRepository<Contact, Long> 
{
	
}
