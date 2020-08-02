package com.general.stores.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {
	
	@GetMapping("/order/payment")
	public String paymentPage() {
		return "payment";
	}

}
