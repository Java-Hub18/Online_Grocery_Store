package com.general.stores.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.general.stores.entity.Contact;
import com.general.stores.service.ContactService;

@Controller
public class ContactController {

	private static Logger log = LoggerFactory.getLogger(ContactController.class);

	private final ContactService contactService;

	@Autowired
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	@PostMapping(value = "/saveContact", produces = MediaType.APPLICATION_JSON_VALUE) // headers =
																						// "Accept=application/json",
																						// produces =
																						// "application/json")
	@ResponseBody
	ResponseEntity<?> addContact(@RequestBody Contact contact, RedirectAttributes redirectAttributes) {
		try {
			log.info("In try....");
			HttpHeaders headers = new HttpHeaders();
			if (contact == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			String name = contact.getName();
			String email = contact.getEmail();
			String subject = contact.getSubject();
			String message = contact.getMessage();
			Date contactDate = new Date();
			log.info("name :: " + name);
			contact.setName(name);
			contact.setEmail(email);
			contact.setSubject(subject);
			contact.setMessage(message);
			contact.setContactDate(contactDate);
			contactService.saveContacts(contact);
			headers.add("User Saved With name - ", name);
			String response = "{\"name\": \"" + name + "\"}";
			return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
