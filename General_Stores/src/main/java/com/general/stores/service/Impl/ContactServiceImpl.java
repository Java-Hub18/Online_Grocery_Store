package com.general.stores.service.Impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.general.stores.entity.Contact;
import com.general.stores.repository.ContactRepository;
import com.general.stores.service.ContactService;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

	private final ContactRepository contactRepository;

	@Autowired
	public ContactServiceImpl(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private Environment env; // to get values from property file.
	
	@Override
	public void saveContacts(Contact contact) throws MessagingException {
		if (contact != null) {
			MimeMessage message = emailSender.createMimeMessage();
			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			String content = "Hi, <b>" + contact.getName()
					+ "</b> has contacted you. Below are the details of his/her message.<br>";
			helper.setSubject(contact.getSubject());
			helper.setText(content + " <b>Name: </b> " + contact.getName() + "<br>" + "" + "<b>Email: </b> "
					+ contact.getEmail() + "<br>"
					+ "<b>Subject: </b> " + contact.getSubject() + "<br>" + "<b>Message:</b> "
					+ contact.getMessage(), true); // set to html
			helper.setFrom(contact.getEmail());
			helper.setTo(env.getProperty("spring.mail.username"));
			//helper.addCc(env.getProperty("mailToCc"));
			emailSender.send(message);

			contactRepository.save(contact);
		}

	}

}
