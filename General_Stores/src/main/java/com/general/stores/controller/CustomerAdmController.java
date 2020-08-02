package com.general.stores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.general.stores.entity.Customer;
import com.general.stores.service.UserAdmService;


@Controller
@RequestMapping("/admin/customer")
public class CustomerAdmController
{
	@Autowired
	UserAdmService userAdmSer;
	
	@GetMapping("/view")
	public String customerView(Model map)
	{
		List<Customer> customerList = userAdmSer.getAllCustomer();
		map.addAttribute("customer", customerList);
		return "admin_customer_view";
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long pid, RedirectAttributes map)
	{
		userAdmSer.deleteCustomer(pid);
		map.addFlashAttribute("delete", "delete");
		return "redirect:/admin/customer/view";
	}
	
}
