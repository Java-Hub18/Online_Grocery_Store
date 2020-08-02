package com.general.stores.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.general.stores.entity.Contact;
import com.general.stores.service.ContactAdmService;

@Controller
@RequestMapping("/admin/contact")
public class ContactAdmController 
{
	private Logger log = Logger.getLogger(ContactAdmController.class);
	
	@Autowired
	ContactAdmService contactAdmSer;
	
	@GetMapping("/view")
	public String getContactList(Model map)
	{
		List<Contact> contactList = contactAdmSer.getAllContact();
		map.addAttribute("contact", contactList);
		return "admin_contact_view";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteContact(@PathVariable("id") Long id, RedirectAttributes map) {
			log.info("Contact id :: "+id);
			contactAdmSer.deleteContact(id);
			map.addFlashAttribute("delete", "d");
			return "redirect:/admin/contact/view";
	}
}
