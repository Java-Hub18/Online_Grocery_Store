package com.general.stores.controller;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.general.stores.entity.Cart;
import com.general.stores.entity.Customer;
import com.general.stores.entity.Order;
import com.general.stores.entity.OrderDetail;
import com.general.stores.entity.Product;
import com.general.stores.service.CartService;
import com.general.stores.service.CustomerService;
import com.general.stores.service.OrderDetailService;
import com.general.stores.service.OrderService;
import com.general.stores.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {
	private static final Logger log = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private ProductService productService;
	
	@Value("${count}")
	private int order_count;
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	CartService cartService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailService orderDetailService;

	@PostMapping("/saveCart")
	String orderCheckout(Customer customer, Product product, HttpServletRequest request, HttpSession session,
			RedirectAttributes rda) {
		try {
			session = request.getSession(false);
			String email = (String) session.getAttribute("email");
			if (email == null) {
				String backUrl = request.getHeader("referer");
				session.setAttribute("backUrl", backUrl);
				return "redirect:/customer/login";
			}
			Customer emailExists = customerService.findCustomerByEmail(email);
			Long customerId = emailExists.getId();
			int count = Integer.parseInt(request.getParameter("item_count"));
			for (int i = 1; i <= count; i++) {
				log.info("item_id = " + Long.parseLong(request.getParameter("item_id_" + i)));
				log.info("item_name = " + request.getParameter("item_name_" + i));
				log.info("quantity = " + Integer.parseInt(request.getParameter("quantity_" + i)));
				log.info("amount = " + Double.parseDouble(request.getParameter("amount_" + i)));
				Long productId = Long.parseLong(request.getParameter("item_id_" + i));
				int quantity = Integer.parseInt(request.getParameter("quantity_" + i));
				double mrpPrice = Double.parseDouble(request.getParameter("mrp_" + i));
				double price = Double.parseDouble(request.getParameter("amount_" + i));
				double totalPrice = quantity * price;
				log.info("total_price = " + totalPrice);
				product.setId(productId);
				customer.setId(customerId);
				Cart cartItems = new Cart(quantity, mrpPrice, price, customer, product, totalPrice);
				List<Cart> cartItemsList = Arrays.asList(cartItems);
				cartService.saveCartItems(cartItemsList);
				log.info("Cart Item Inserted :"+i);
			}
			rda.addFlashAttribute("cart", "cart");
			return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}
	}

	@GetMapping("/my-cart")
	String showMyCartItems(Customer customer, HttpServletRequest request, HttpSession session, Model model) {
		try {
			session = request.getSession(false);
			String email = (String) session.getAttribute("email");
			if (email != null) {
				Customer emailExists = customerService.findCustomerByEmail(email);
				Long customerId = emailExists.getId();
				customer.setId(customerId);
				List<Cart> cartItemsList = cartService.getCartItemsByCustomerId(customer);
				double total_mrp = 0;
				int total_qty = 0;
				double total_price = 0;
				double total_saving = 0;
				for(Cart c: cartItemsList) {
					total_qty += c.getQuantity();
					total_mrp += c.getMrpPrice() * c.getQuantity();
					total_price += c.getPrice() * c.getQuantity();
				}
				total_saving = total_mrp - total_price;
				model.addAttribute("total_saving", total_saving);
				model.addAttribute("total_mrp", total_mrp);
				model.addAttribute("total_qty", total_qty);
				model.addAttribute("total_price", total_price);
				model.addAttribute("items", cartItemsList);
				return "cart-list";
			}
			return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}
	}

	@PostMapping("/checkout")
	String productCheckout(@RequestParam("code") String code, HttpServletRequest request, HttpSession session, 
			Product product, Model model, Cart cart, Customer customer) {
		try {
			session = request.getSession(false);
			String email = (String) session.getAttribute("email");
			if(email == null) {
				String backUrl = request.getHeader("referer");
				log.info("backUrl :: "+backUrl);
				session.setAttribute("backUrl", backUrl);
				return "redirect:/customer/login";
			}
			log.info("Code :: "+code);
			if (code != null && code.startsWith("P")) {
				product = productService.getProductByCode(code);
				log.info("product :: " + product.getName());
				if (product != null) {
					product.setId(product.getId());
					customer = customerService.findCustomerByEmail(email);
					Long customerId = customer.getId();
					customer.setId(customerId);
					cart = new Cart(1, product.getMrpPrice(), product.getPrice(), customer, product, product.getPrice());
					cartService.saveCart(cart);
					log.info("Cart : Item Inserted.");
					return "redirect:/cart/my-cart";
				}
			}
			return "redirect:/page404";
		}  catch (Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}
	}
	
	@GetMapping(value = "/remove/{cid}")
	String removeCartItems(@PathVariable("cid") Long cartId, HttpServletRequest request, HttpSession session,
			RedirectAttributes rda, Customer customer, Product product) {
		String email = "";
		try {
			session = request.getSession(false);
			email = (String) session.getAttribute("email");
			if (email != null) {
				Long customerId = customerService.getCustomerId(email);
				customer.setId(customerId);
				cartService.removeCartItems(customer, cartId);
				rda.addFlashAttribute("delete", "delete");
				return "redirect:/cart/my-cart";
			}
			return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			rda.addFlashAttribute("error", "error");
			return "redirect:/cart/my-cart";
		}
	}

	@PostMapping("/order/checkout")
	String cartItemsCheckout(@RequestParam("customerName") String customerName,
			@RequestParam("customerPhone") String customerPhone, @RequestParam("address") String customerAddress,
			@RequestParam("addressType") String customerAddressType, @RequestParam("pinCode") String pinCode, HttpServletRequest request, HttpSession session,
			Order order, OrderDetail od, Product product, Customer customer, RedirectAttributes rda) {
		int x, y, count, orderNum, paymentId;
		x = y = orderNum = paymentId = count = 0;
		String orderStatus = "Pending";
		double total = 0;
		double total_mrp=0;
		Date orderDate = new Date();
		try {
			session = request.getSession(false);
			String customerEmail = (String) session.getAttribute("email");
			if (customerEmail != null) {
				Pageable pageable = PageRequest.of(0, 1);
				List<OrderDetail> orderList = orderDetailService.getLastOrderByIdDesc(pageable);
				if(orderList.size() == 0) {
					paymentId = order_count;
					log.info("In List Size 0, Payment Id :: "+paymentId);
				} else {
					paymentId = orderList.get(0).getPaymentId() + 1;
					log.info("In else, Payment Id :: "+paymentId);
				}
				count = (Integer) session.getAttribute("size");
				String paymentMode = request.getParameter("paymentMode");
				int total_qty = 0;
				for (int i = 1; i <= count; i++) {
					log.info("Cart Id :: " + request.getParameter("cid_" + i));
					log.info("Product Id :: " + request.getParameter("pid_" + i));
					log.info("Product Name :: " + request.getParameter("pname_" + i));
					log.info("Product code :: " + request.getParameter("code_" + i));
					log.info("Product Quantity :: " + request.getParameter("quantity_" + i));
					log.info("Product MRP :: " + request.getParameter("mrp_" + i));
					log.info("Product Amount :: " + request.getParameter("amount_" + i));
					log.info("Product Total Amount :: " + request.getParameter("total_amount_" + i));
					Long ordersCount = orderService.getOrdersCount();
					log.info("Total Order Count :: "+ordersCount);
					if(ordersCount == 0) {
						orderNum = order_count;
						log.info("In If :: orderNum :: "+orderNum);
					}
					orderNum = ordersCount.intValue() + order_count;
					log.info("Not In If :: orderNum :: "+orderNum);
					double totalAmount = Double.parseDouble(request.getParameter("total_amount_" + i));
					boolean active = true;
					Long productId = Long.parseLong(request.getParameter("pid_" + i));
					Long cartId = Long.parseLong(request.getParameter("cid_" + i));
					int quantity = Integer.parseInt(request.getParameter("quantity_" + i));
					double amount = Double.parseDouble(request.getParameter("amount_" + i));
					double mrpPrice = Double.parseDouble(request.getParameter("mrp_" + i));
					total_qty += quantity;
					double totalPrice = quantity * amount;
					total += quantity * amount;
					total_mrp += mrpPrice * quantity;
					product.setId(productId);
					log.info("orderNum :: "+orderNum);
					order = new Order(orderNum, totalAmount, customerName, customerAddress, customerAddressType, customerEmail, customerPhone, pinCode, active, orderDate);
					List<Order> orders = Arrays.asList(order);
					orderService.saveOrders(orders);
					log.info("======================================");
					Long id = orderService.getOrderIdByNum(orderNum);
					order.setId(id);
					x++;
					log.info("order id =" + id +" x = "+x);
					boolean flag = orderDetailService.saveCartOrderDetail(order, product, quantity, mrpPrice, amount, totalPrice, paymentId, orderStatus, paymentMode);
					log.info("Flag = "+flag);
					if (flag) {
						y++;
						log.info("order detail saved. y = " + y);
					}
					Long customerId = customerService.getCustomerId(customerEmail);
					customer.setId(customerId);
					cartService.removeCartItems(customer, cartId);
				}
				double totalSavings = total_mrp - total;
				rda.addFlashAttribute("orderDate", orderDate);
				rda.addFlashAttribute("totalAmount", total);
				rda.addFlashAttribute("totalMrp", total_mrp);
				rda.addFlashAttribute("paymentId", paymentId);
				rda.addFlashAttribute("pinCode", pinCode);
				rda.addFlashAttribute("totalQty", total_qty);
				rda.addFlashAttribute("totalSavings", totalSavings);
				rda.addFlashAttribute("success", "success");
				return "redirect:/order/payment";
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (x != count) {
				log.info("In catch :: "+orderNum);
				orderService.deleteOrdersByNum(orderNum);
				log.info("deleting from orders table. " + x);
				rda.addFlashAttribute("error", "error");
			}
			if (y != count) {
				log.info("In catch :: "+paymentId);
			orderDetailService.deleteOrderDetailByNum(paymentId);
			log.info("deleting from order_detail table. " + y);
			rda.addFlashAttribute("error", "error");
			return "redirect:/order/payment";
		}
	}
		return "redirect:/home";
 }
}