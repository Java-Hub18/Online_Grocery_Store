package com.general.stores.service;

import javax.mail.MessagingException;
import com.general.stores.entity.Contact;

public interface ContactService {
	
	void saveContacts(Contact contact) throws MessagingException;

}
