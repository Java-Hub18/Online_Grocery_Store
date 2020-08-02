package com.general.stores.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.general.stores.entity.Customer;
import com.general.stores.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	// @InitBinder - pre process every string data.
	// removes the leading & trailing white spaces.
	// If string only has white space .... trim it to null.

	@Value("${email.expire.time}")
	private long timeInHours;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor ste = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, ste);
	}

	@Autowired
	private CustomerService customerService;

	// Inject BCryptPasswordEncoder for encoding password, default length is 60.+
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private Environment env;

	@GetMapping("/login")
	public String pageLogin(Customer customer, Model model) {
		model.addAttribute("customerLoginForm", new Customer());
		return "login";
	}

	@GetMapping("/register")
	public String pageRegister(Customer customer, Model model) {
		model.addAttribute("customerForm", new Customer());
		return "register";
	}

	@GetMapping("/forgot-password")
	public ModelAndView CustomerForgotPasswordPage(Customer Customer) {
		return new ModelAndView("forgot-password", "forgot-password", Customer);
	}

	@GetMapping("/changepassword")
	public ModelAndView CustomerChangePassword() {
		return new ModelAndView("changepassword");
	}

	@GetMapping(value = { "/change-password/", "/change-password" })
	public ModelAndView CustomerChangePasswordPage(@RequestParam("email") String encodedEmail,
			@RequestParam("token") String token, @RequestParam("data") String data) {
		log.info("In change-password ...");
		if (encodedEmail == null || token == null || data == null) {
			return new ModelAndView("redirect:/page404");
		}
		byte[] decodedBytes = Base64.getDecoder().decode(encodedEmail);
		String email = new String(decodedBytes);
		Customer emailExist = customerService.findCustomerByEmail(email);
		if (emailExist == null) {
			return new ModelAndView("redirect:/page404");
		} else {
			byte[] decodedTime = Base64.getDecoder().decode(data);
			String time = new String(decodedTime);
			Date todayDateTime = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dataTime = sdf.format(todayDateTime);
			log.debug("time = " + time + " emailTime = " + time + " dataTime = " + dataTime);
			Date d1 = null;
			Date d2 = null;
			try {
				d1 = sdf.parse(time);
				d2 = sdf.parse(dataTime);
			} catch (ParseException e) {
				e.printStackTrace();
				return new ModelAndView("redirect:/page404");
			}
			long diff = d2.getTime() - d1.getTime();
			long diffHours = diff / (60 * 60 * 1000);
			log.debug("dataTime = " + d1 + " currTime = " + d2 + " Diff=" + diff + " emailExpireTime=" + timeInHours
					+ " diffHours=" + diffHours);
			if (diffHours >= timeInHours) {
				log.info("Time expired.");
				return new ModelAndView("redirect:/page404");
			}
		}
		return new ModelAndView("change-password");
	}

	@GetMapping("/logout")
	public String CustomerLogoutPage(Customer customer, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		session = request.getSession();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		session.invalidate();
		return "logout";
	}

	@PostMapping("/saveCustomer")
	String createCustomer(@Valid @ModelAttribute("customerForm") Customer customer, BindingResult br, Model model,
			HttpSession session) {
		try {
			if (br.hasErrors()) {
				log.info("BindingResult Found an error.");
				return "register";
			} else {
				String email = customer.getEmail();
				String name = customer.getName();
				String address = customer.getAddress();
				String phone = customer.getPhone();
				String password = customer.getPassword();
				String pinCode = customer.getPinCode();
				String encryptedPassword = bCryptPasswordEncoder.encode(password);
				customer.setPassword(encryptedPassword);
				customer.setCreateDate(new Date());
				customer.setValid(true);
				Customer emailExists = customerService.findCustomerByEmail(email);
				Customer phoneExists = customerService.findCustomerByPhone(phone);
				if (emailExists != null) {
					br.rejectValue("email", "error.customer", "This email already exists!");
					log.info("This email already exists!");
					return "register";
				} else if (phoneExists != null) {
					br.rejectValue("phone", "error.phone", "This phone already exists!");
					log.info("This phone already exists!");
					return "register";
				} else {
					log.info("in else, Saving Customer.");
					customerService.saveCustomer(customer);
					if(name.length() > 15) {
						String[] names = name.split(" ");
						session.setAttribute("name", names[0]);
					} else {
						session.setAttribute("name", name);
					}
					session.setAttribute("phone", phone);
					session.setAttribute("email", email);
					session.setAttribute("address", address);
					session.setAttribute("pinCode", pinCode);
					return "redirect:/home";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return "register";
		}
	}

	@PostMapping("/loginCustomer")
	String validateCustomer(@Valid @ModelAttribute("customerLoginForm") Customer customer, BindingResult br,
			Model model, HttpSession session) {
		try {

			String email = customer.getEmail();
			String rawPassword = customer.getPassword();
			Customer emailExists = customerService.findCustomerByEmail(email);
			if (emailExists == null) {
				br.rejectValue("email", "error.customer", "This email is not registered.");
				log.info("This email is not registered.");
				return "login";
			} else {
				String encodedPassword = emailExists.getPassword(); // customerService.findCustomerPassword(email);
				boolean checkPassStatus = bCryptPasswordEncoder.matches(rawPassword, emailExists.getPassword());
				if (checkPassStatus) {
					boolean status = customerService.loginCustomer(email, encodedPassword);
					if (status) {
						String name = emailExists.getName();						
						String address = emailExists.getAddress();
						String phone = emailExists.getPhone();
						String pinCode = emailExists.getPinCode();
						log.info("in else, loging Customer.");
						if(name.length() > 15) {
							String[] names = name.split(" ");
							session.setAttribute("name", names[0]);
						} else {
							session.setAttribute("name", name);
						}
						session.setAttribute("phone", phone);
						session.setAttribute("email", email);
						session.setAttribute("address", address);
						session.setAttribute("pinCode", pinCode);
						String url = (String) session.getAttribute("backUrl");
						log.info("in Login, backUrl :: "+url);
						if (url != null) {
							if (url.contains("8888")) {
								log.info("In 8888");
								String[] newUrl = url.split("8888");
								String backUrl = newUrl[1];
								log.info("Back Url: " + backUrl);
								session.removeAttribute("url");
								return "redirect:" + backUrl;
							} else if (url.contains(".com")) {
								log.info("In Domain");
								String[] newUrl = url.split(".com");
								String backUrl = newUrl[1];
								log.info("Back Url: " + backUrl);
								session.removeAttribute("url");
								return "redirect:" + backUrl;
							} else {
								log.info("In else, No Back Url");
								session.removeAttribute("url");
								return "redirect:/home";
							}
						}
						return "redirect:/home";
					} else {
						br.rejectValue("password", "error.customer", "Password mismatch.");
						return "login";
					}
				} else {
					br.rejectValue("password", "error.customer", "Password doesn't match.");
					return "login";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return "login";
		}
	}

	@PostMapping("/forgotPassword")
	public ModelAndView forgotPassword(@Valid @ModelAttribute("forgot-password") Customer Customer,
			BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
		if (Customer.getEmail() == null) {
			log.info("in br, Email can't be empty.");
			return new ModelAndView("forgot-password");
		}
		String email = Customer.getEmail();
		log.info("Customer Email = " + email);
		Customer CustomerExist = customerService.findCustomerByEmail(email);
		if (CustomerExist != null) {
			log.info("Customer Exist True.");
			try {
				customerService.sendMail(CustomerExist);
				log.info("Sending Done...");
				redirectAttributes.addFlashAttribute("success", "Success");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("error", "Error");
				log.debug("Exception while sending email = " + e);
			}
			return new ModelAndView("redirect:/customer/forgot-password");
		} else {
			log.info("Customer Exist False.");
			bindingResult.rejectValue("email", "error.Customer", "Could not find a Customer with this email.");
			return new ModelAndView("forgot-password");
		}
	}

	@PostMapping("/changePassword")
	ModelAndView changePassword(@RequestParam("email") String encodedEmail, @RequestParam("password") String password,
			@RequestParam("confirm-password") String confirmPassword, @RequestParam("token") String token,
			@RequestParam("data") String data, final RedirectAttributes redirectAttributes) {
		if (encodedEmail == null || token == null || data == null || password == null || confirmPassword == null) {
			return new ModelAndView("redirect:/page404");
		}
		byte[] decodedBytes = Base64.getDecoder().decode(encodedEmail);
		String email = new String(decodedBytes);
		byte[] decodedTime = Base64.getDecoder().decode(data);
		String time = new String(decodedTime);
		Date todayDateTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(todayDateTime);
		log.debug("decodedTime = " + time + " currentTime = " + currentTime);
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = sdf.parse(time);
			d2 = sdf.parse(currentTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long diff = d2.getTime() - d1.getTime();
		long diffHours = diff / (60 * 60 * 1000);
		long hours = Long.valueOf(diffHours);
		log.debug("dataTime = " + d1 + " currTime = " + d2 + " Diff=" + diff + " emailExpireTime=" + timeInHours
				+ " diffHours=" + hours);
		if (hours >= timeInHours) {
			log.info("Time expired.");
			return new ModelAndView("redirect:/page404");
		}
		String emailUrl = env.getProperty("email-call-back-url");
		log.info("encode email " + encodedEmail);
		log.info("decoded email " + email + "emailUrl " + emailUrl + " token " + token + " " + data);
		if (email != null && !email.equals("")) {
			if (password == null || confirmPassword == null) {
				log.info("Email & Password can't be empty.");
				redirectAttributes.addFlashAttribute("error", "error");
				return new ModelAndView(
						"redirect:" + emailUrl + "?email=" + encodedEmail + "&token=" + token + "&data=" + data + "");
			}
			if (!password.equals(confirmPassword)) {
				redirectAttributes.addFlashAttribute("unmatched", "unmatched");
				return new ModelAndView(
						"redirect:" + emailUrl + "?email=" + encodedEmail + "&token=" + token + "&data=" + data + "");
			}
			Customer customerExist = customerService.findCustomerByEmail(email);
			if (customerExist != null) {
				log.info("Customer Exist True.");
				log.info("Time okay.");
				String encryptedPassword = bCryptPasswordEncoder.encode(password);
				customerService.updatePassword(email, encryptedPassword);
				log.info("Update Done...");
				redirectAttributes.addFlashAttribute("success", "Success");
				return new ModelAndView(
						"redirect:" + emailUrl + "?email=" + encodedEmail + "&token=" + token + "&data=" + data + "");
			} else {
				log.info("Customer Exist False.");
				redirectAttributes.addFlashAttribute("notFound", "notFound");
				return new ModelAndView(
						"redirect:" + emailUrl + "?email=" + encodedEmail + "&token=" + token + "&data=" + data + "");
			}
		} else {
			return new ModelAndView("redirect:/page404");
		}
	}

	@PostMapping("/changeCustomerPassword")
	ModelAndView changeCustomerPassword(@RequestParam("password") String password,
			@RequestParam("confirm-password") String confirmPassword, HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		session = request.getSession(false);
		String email = (String) session.getAttribute("email");
		log.info("email " + email + "password " + password + " confirmPassword " + confirmPassword);
		if (email != null && !email.equals("")) {
			if (password == null || confirmPassword == null) {
				log.info("Email & Password can't be empty.");
				redirectAttributes.addFlashAttribute("error", "error");
				return new ModelAndView("redirect:/customer/changepassword");
			}
			if (!password.equals(confirmPassword)) {
				redirectAttributes.addFlashAttribute("unmatched", "unmatched");
				return new ModelAndView("redirect:/customer/changepassword");
			}
			Customer CustomerExist = customerService.findCustomerByEmail(email);
			if (CustomerExist != null) {
				log.info("Customer Exist True.");
				log.info("Time okay.");
				String encryptedPassword = bCryptPasswordEncoder.encode(password);
				customerService.updatePassword(email, encryptedPassword);
				log.info("Update Done...");
				redirectAttributes.addFlashAttribute("success", "Success");
				return new ModelAndView("redirect:/customer/changepassword");
			} else {
				log.info("Customer Exist False.");
				return new ModelAndView("redirect:/page404");
			}
		} else {
			return new ModelAndView("redirect:/page404");
		}
	}

	@GetMapping("/my-account")
	public ModelAndView editCustomer(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		String email = (String) session.getAttribute("email");
		log.info("update customer..." + email);
		Customer customer = customerService.findCustomerByEmail(email);
		if (customer != null) {
			mav = new ModelAndView("my-account");
			log.info("In Account :: "+customer.getId() + " "+ customer.getGender());
			mav.addObject("customerUpdate", customer);
			mav.addObject("gender", customer.getGender());
			return mav;
		} else {
			mav = new ModelAndView("/logout");
			return mav;
		}
	}
	
	@PostMapping("/updateCustomer")
	String updateCustomer(@Valid @ModelAttribute("customerUpdate") Customer customer, BindingResult br,
			HttpSession session, Model model) {
		try {
				Long id = customer.getId();
				String name = customer.getName();
				String address = customer.getAddress();
				String phone = customer.getPhone();
				String gender = customer.getGender();
				String pinCode = customer.getPinCode();
				log.info(customer.getId() + " "+ customer.getGender());
				if (id != null) {
					log.info("in else, Updating Customer.");
					customerService.updateCustomerById(id, name, address, gender, phone, pinCode);
					session.removeAttribute("name");
					session.removeAttribute("phone");
					session.removeAttribute("address");
					session.removeAttribute("pinCode");

					session.setAttribute("name", name);
					session.setAttribute("phone", phone);
					session.setAttribute("address", address);
					session.setAttribute("pinCode", pinCode);
					model.addAttribute("gender", gender);
					model.addAttribute("success", "success");
					return "my-account";
				}
			model.addAttribute("error", "error");
			return "my-account";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "error");
			return "my-account";
		}
	}
}
