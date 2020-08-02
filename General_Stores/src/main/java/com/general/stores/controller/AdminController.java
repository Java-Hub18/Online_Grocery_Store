package com.general.stores.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.general.stores.entity.Admin;
import com.general.stores.entity.OrderDetail;
import com.general.stores.service.AdminService;
import com.general.stores.service.OrderDetailAdmService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor ste = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, ste);
	}

	@Autowired
	private AdminService adminService;
	
	@Autowired
	MailSender mailSender;

	@Autowired
	private OrderDetailAdmService orderDetailAdmService;

	@GetMapping(value = {"/", "", "/login"})
	String adminLoginPage(Model model) {
		model.addAttribute("AdminLoginForm", new Admin());
		return "admin";
	}
	
	@GetMapping("/forgot-password") 
	String adminForgootPasswordPage() {	
		return "admin_forgot_pass";
	}
	
	@GetMapping("/home") 
	String adminHomePage(HttpSession session, Model map) {
		String aemail = (String) session.getAttribute("aemail");
		if(aemail == null) {
			return "redirect:/home";
		}
		String orderStatus = "Pending";
		Pageable pageable = PageRequest.of(0, 10);
		List<OrderDetail> pending = orderDetailAdmService.getTop10Orders(orderStatus, pageable);
		//log.info("Pending Orders ::"+pending);
		map.addAttribute("pending", pending);
		return "home";
	}

	@GetMapping("/logout")
	public String AdminLogoutPage(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		session = request.getSession();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		session.invalidate();
		return "admin_logout";
	}
	
	@PostMapping("/home")
	String AdminLogin(@Valid @ModelAttribute("AdminLoginForm") Admin admin, BindingResult br, Model model,
			HttpSession session) {
		try {
			String email = admin.getEmail();
			String password = admin.getPassword();
			if (email == null && password == null) {
				return "admin";
			}
			Admin emailExists = adminService.getAdminByEmail(email);
			if (emailExists == null) {
				br.rejectValue("email", "error.admin", "This email is not registered.");
				log.info("This email is not registered.");
				return "admin";
			} else {
				admin = adminService.validateAdmin(email, password);
				if (admin != null) {
					String aname = admin.getName();
					session.setAttribute("aname", aname);
					session.setAttribute("id", admin.getId());
					session.setAttribute("aemail", email);
					String orderStatus = "Pending";
					List<OrderDetail> pending = orderDetailAdmService.getOrdersByStatus(orderStatus);
					//log.info("Pending Orders ::"+pending);
					model.addAttribute("pending", pending);
					return "home";
				} else {
					br.rejectValue("password", "error.admin", "Password doesn't match.");
					log.info("Invalid Password.");
					return "admin";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin";
	}
	
	@GetMapping("/my-account")
	String account(Admin admin, Model map, HttpSession session) {
		String aemail = (String) session.getAttribute("aemail");
		if(aemail == null) {
			return "redirect:/home";
		}
		admin = adminService.getAdminByEmail(aemail);
		map.addAttribute("name",admin.getName());
		map.addAttribute("email",admin.getEmail());
		return "admin_account";
	}
	
	@PostMapping("/updateProfile")
	String updateAdminProfile(@RequestParam("name") String name, @RequestParam("email") String email,
			final RedirectAttributes redirectAttributes, HttpSession session) {
		
		Long id = (Long) session.getAttribute("id");
		if(id == null) {
			return "redirect:/page404";
		}
		try {
			adminService.updateAdminByEmail(name, email, id);
			session.removeAttribute("aname");
			session.removeAttribute("email");
			
			session.setAttribute("aname", name);
			session.setAttribute("aemail", email);
			redirectAttributes.addFlashAttribute("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "error");
		}
		return "redirect:/admin/my-account";
	}
	@GetMapping("/changepassword")
	public String changePassword() {
		return "admin_changepass";
	}
	
	@PostMapping("/changeAdminPassword")
	ModelAndView changeCustomerPassword(@RequestParam("password") String password,
			@RequestParam("confirm_password") String confirmPassword, HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		session = request.getSession(false);
		String email = (String) session.getAttribute("aemail");
		log.info("email " + email + "password " + password + " confirmPassword " + confirmPassword);
		if (email != null && !email.equals("")) {
			if (password == null || confirmPassword == null) {
				log.info("Email & Password can't be empty.");
				redirectAttributes.addFlashAttribute("error", "error");
				return new ModelAndView("redirect:/admin/changepassword");
			}
			if (!password.equals(confirmPassword)) {
				redirectAttributes.addFlashAttribute("unmatched", "unmatched");
				return new ModelAndView("redirect:/admin/changepassword");
			}
			Admin adminExist = adminService.getAdminByEmail(email);
			if (adminExist != null) {
				log.info("Admin Exist True.");
				adminService.changePassword(password, email);
				log.info("Update Done...");
				redirectAttributes.addFlashAttribute("success", "Success");
				return new ModelAndView("redirect:/admin/changepassword");
			} else {
				log.info("Admin Exist False.");
				return new ModelAndView("redirect:/page404");
			}
		} else {
			return new ModelAndView("redirect:/page404");
		}
	}

	@PostMapping("/forgotPassword")
	public String forgotPassword(@RequestParam(name ="email", required = false) String email, Admin admin, RedirectAttributes rda) {
		log.info("email ::" + email);
		if(email == null) {
			log.info("Email is Empty.");
			rda.addFlashAttribute("blank", "blank");
			return "redirect:/admin/forgot-password";
		}
		try {
			admin = adminService.getAdminByEmail(email);
			if(admin == null) {
				rda.addFlashAttribute("notFound", "authno");
				return "redirect:/admin/forgot-password";
			}
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(email);
			msg.setSubject("Dharmesh General Store : Admin Forgot Password");
			msg.setText("Hi Your Password is: " + admin.getPassword());
			mailSender.send(msg);
			rda.addFlashAttribute("success", "success");
			return "redirect:/admin/forgot-password";
		} catch (MailException e) {
			e.printStackTrace();
			rda.addFlashAttribute("error", "error");
			return "redirect:/admin/forgot-password";	
		}
	}
}
