package com.general.stores.service;

import java.util.List;
import java.util.Optional;
import com.general.stores.entity.Contact;

public interface ContactAdmService 
{
	public Optional<Contact> getContactId(Long pid);
	public List<Contact> getAllContact();
	public void deleteContact(Long pid);
	public void deleteAll(List<Contact> ids);
}
