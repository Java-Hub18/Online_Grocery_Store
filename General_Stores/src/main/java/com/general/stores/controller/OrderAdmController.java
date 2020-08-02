package com.general.stores.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.general.stores.entity.Order;
import com.general.stores.entity.OrderDetail;
import com.general.stores.service.OrderAdmService;
import com.general.stores.service.OrderDetailAdmService;

@Controller
@RequestMapping("/admin/order")
public class OrderAdmController 
{
	 private Logger log = Logger.getLogger(OrderAdmController.class);
	 
	 @Autowired
	 OrderAdmService orderAdmSer;
	 
	 @Autowired
	 private OrderDetailAdmService orderDetailAdmService;
	 
	 @Autowired
	 OrderDetailAdmService orderDetAdmSer;
	 
	 @GetMapping("/view")
     public String orderList(Model map)
     {
		 List<OrderDetail> orderList = orderDetailAdmService.getAllOrders();
		 map.addAttribute("orders", orderList);
    	 return "admin_order_view";
     }
	 
	 @GetMapping("/pending-orders")
	 public String pendingOrders(HttpSession session, Model map) {
			String aemail = (String) session.getAttribute("aemail");
			if(aemail == null) {
				return "redirect:/home";
			}
			String orderStatus = "Pending";
			List<OrderDetail> pending = orderDetailAdmService.getOrdersByStatus(orderStatus);
			//log.info("Pending Orders ::"+pending);
			map.addAttribute("pending", pending);
		 return "admin_order_pending";
	 }
	 
	 @GetMapping("/delivered-orders")
	 public String deleveredOrders(HttpSession session, Model map) {
			String aemail = (String) session.getAttribute("aemail");
			if(aemail == null) {
				return "redirect:/home";
			}
			String orderStatus = "Delivered";
			List<OrderDetail> delivered = orderDetailAdmService.getOrdersByStatus(orderStatus);
			//log.info("delivered Orders ::"+delivered);
			map.addAttribute("delivered", delivered);
		 return "admin_order_delivered";
	 }
	 
	 @GetMapping("/cancelled-orders")
	 public String cancelledOrders(HttpSession session, Model map) {
			String aemail = (String) session.getAttribute("aemail");
			if(aemail == null) {
				return "redirect:/home";
			}
			String orderStatus = "Cancelled";
			List<OrderDetail> cancel = orderDetailAdmService.getOrdersByStatus(orderStatus);
			//log.info("Cancelled Orders ::"+cancel);
			map.addAttribute("cancel", cancel);
		 return "admin_order_cancelled";
	 }
	 
	 @GetMapping("/changeStatus/{orderStatus}/{id}")
	 public String changeStatusOfOrders(@PathVariable("orderStatus") String  orderStatus, @PathVariable("id") Long id,
			 RedirectAttributes rda, HttpServletRequest request, HttpSession session)
	 {
		 String aemail = (String) session.getAttribute("aemail");
			if(aemail == null) {
				return "redirect:/home";
			}
			log.info("orderStatus :: "+orderStatus +" id :: "+id);
			orderAdmSer.changeOrderStatus(orderStatus, id);
			String backUrl = request.getHeader("referer");
			log.info("back URL : "+backUrl);
			if (orderStatus.equals("Delivered")) {
				rda.addFlashAttribute("delivered", "delivered");
				String newUrl[] = backUrl.split("admin/");
				log.info("new Url : "+newUrl[1]);
				return "redirect:/admin/"+newUrl[1];
			} else if (orderStatus.equals("Pending")) {
				rda.addFlashAttribute("pendings", "pending");
				String newUrl[] = backUrl.split("admin/");
				log.info("new Url : "+newUrl[1]);
				return "redirect:/admin/"+newUrl[1];
			} else if (orderStatus.equals("Cancelled")) {
				if(aemail.equals("email")) {
					log.info("in if");
					rda.addFlashAttribute("cancelled", "cancelled");
					return "redirect:/orders/my-orders";
				}
				rda.addFlashAttribute("cancelled", "cancelled");
				String newUrl[] = backUrl.split("admin/");
				log.info("new Url : "+newUrl[1]);
				return "redirect:/admin/"+newUrl[1];
			} else {
				return "redirect:/home";
			}
	 }
	
	 @GetMapping("/delete/{id}")
	 public String removeOrder(@PathVariable("id") Long orderId, Order order, 
			HttpServletRequest request ,HttpSession session, RedirectAttributes rda) {
		 String aemail = (String) session.getAttribute("aemail");
			if(aemail == null || orderId == 0) {
				return "redirect:/home";
			}
			try {
				log.info("id :: "+orderId);
				orderAdmSer.deleteOrdersById(orderId);
				log.info("deleted from Order Table.");
				order.setId(orderId);
				orderAdmSer.deleteOrderDetailById(order);
				log.info("deleted from OrderDetail Table.");
				rda.addFlashAttribute("delete", "delete");
				String backUrl = request.getHeader("referer");
				log.info("back URL : "+backUrl);
				String newUrl[] = backUrl.split("admin/");
				log.info("new Url : "+newUrl[1]);
				return "redirect:/admin/"+newUrl[1];
			} catch (Exception e) {
				rda.addFlashAttribute("error", "error");
				e.printStackTrace();
				return "redirect:/home";
			}
	 }
}
