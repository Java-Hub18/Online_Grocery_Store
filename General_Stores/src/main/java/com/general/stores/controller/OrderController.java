package com.general.stores.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.general.stores.entity.Order;
import com.general.stores.entity.OrderDetail;
import com.general.stores.entity.Product;
import com.general.stores.service.OrderDetailService;
import com.general.stores.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${count}")
	private int order_count;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailService orderDetailService;

	@PostMapping("/checkout")
	String checkOutOrder(@RequestParam("customerName") String customerName,
			@RequestParam("customerPhone") String customerPhone, @RequestParam("address") String customerAddress,
			@RequestParam("addressType") String customerAddressType, @RequestParam("pinCode") String pinCode, HttpServletRequest request, HttpSession session,
			Order order, OrderDetail od, Product product, Model model) {
		int x, y, orderNum, paymentId;
		x = y = orderNum = paymentId = 0;
		try {
			session = request.getSession(false);
			String customerEmail = (String) session.getAttribute("email");
			if (customerEmail == null) {
				return "redirect:/home";
			}
			String orderStatus = "Pending";
			Long productId = (Long) session.getAttribute("itemId");
			int quantity = (Integer) session.getAttribute("quantity");
			double mrpPrice = (Double) session.getAttribute("mrpPrice");
			double amount = (Double) session.getAttribute("price");
			double totalAmount = (Double) session.getAttribute("totalPrice");
			Date orderDate = new Date();
			boolean active = true;
			Long orderCount = orderService.getOrdersCount();
			log.info("Count :: "+orderCount);
			if(orderCount == 0) {
				paymentId = order_count;
				orderNum = order_count;
			}
			orderNum = orderCount.intValue() + order_count;
			Pageable pageable = PageRequest.of(0, 1);
			List<OrderDetail> orderList = orderDetailService.getLastOrderByIdDesc(pageable);
			if(orderList.size() == 0) {
				paymentId = order_count;
				log.info("In List Size 0 , OrderNum :: "+orderNum+" Payment Id :: "+paymentId);
			} else {
				paymentId = orderList.get(0).getPaymentId() + 1;
				log.info("In else, OrderNum :: "+orderNum+" Payment Id :: "+paymentId);
			}
			order.setOrderNum(orderNum);
			order.setCustomerName(customerName);
			order.setCustomerEmail(customerEmail);
			order.setCustomerPhone(customerPhone);
			order.setCustomerAddress(customerAddress);
			order.setCustomerAddressType(customerAddressType);
			order.setPinCode(pinCode);
			order.setAmount(amount);
			order.setActive(active);
			order.setOrderDate(orderDate);
			boolean status = orderService.saveProductOrder(order);
			if (status) {
				x = 1;
				log.info("order saved. x = " + x);
				Order o = new Order();
				o.setOrderNum(orderNum);
				od.setOrder(order);
				product.setId(productId);
				od.setProduct(product);
				od.setQuantity(quantity);
				od.setMrpPrice(mrpPrice);
				od.setPrice(amount);
				od.setAmount(totalAmount);
				od.setPaymentId(paymentId);
				od.setOrderStatus(orderStatus);
				boolean flag = orderDetailService.saveOrderDetail(od);
				if (flag) {
					y = 1;
					log.info("order detail saved. y = " + y);
					model.addAttribute("orderDate", orderDate);
					model.addAttribute("totalAmount", totalAmount);
					model.addAttribute("paymentId", paymentId);
					model.addAttribute("pinCode", pinCode);
					model.addAttribute("success", "success");
				}
			}
			return "payment";
		} catch (Exception e) {
			e.printStackTrace();
			if (x == 0) {
				orderService.deleteOrdersByNum(orderNum);
				log.info("deleting from orders table. " + x);
			}
			if (y == 0) {
				orderDetailService.deleteOrderDetailByNum(paymentId);
				log.info("deleting from order_detail table. " + y);
			}
			model.addAttribute("error", "error");
			return "payment";
		}
	}
}
