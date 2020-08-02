package com.general.stores.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.general.stores.entity.Customer;
import com.general.stores.repository.CustomerRepository;
import com.general.stores.service.CustomerService;
import com.general.stores.util.CustomerUtility;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CustomerRepository customerRepository;	
	
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private Environment env;

	
	@Override
	public void saveCustomer(Customer customer) {
		try {
			customerRepository.save(customer);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	@Override
	public boolean loginCustomer(String email, String password) {
		return customerRepository.findValidCustomer(email, password);
	}

	@Override
	public Customer findCustomerByEmail(String email) {
		return customerRepository.checkCustomerByEmail(email);
	}

	@Override
	public String findCustomerPassword(String email) {
		return customerRepository.getCustomerPasswordByEmail(email);
	}

	@Override
	public void sendMail(Customer customer) {
		try {
			if (customer != null) {
				String emailCallBackUrl = env.getProperty("email.call.back.url");
				String token = CustomerUtility.generateNewToken();
				String email = customer.getEmail();
				String encodeEmail = Base64.getEncoder().encodeToString(email.getBytes());
				Date todayDateTime = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dataTime = sdf.format(todayDateTime);
				Date d1 = null;
				try {
				    d1 = sdf.parse(dataTime);
				} catch (ParseException e) {
				    e.printStackTrace();
				}    
				String data = String.valueOf(d1);
				System.out.println("In Service ="+data+ " "+dataTime);
				String encodeData = Base64.getEncoder().encodeToString(dataTime.getBytes());
				MimeMessage message = emailSender.createMimeMessage();
				// use the true flag to indicate you need a multipart message
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				String content = "Hi <b>" + customer.getName()
						+ "</b>,<br>A password reset for your account was requested.<br>"
						+ "Please click the button below to change your password.<br>"
						+ "Note that this link is valid for <b>24</b> hours. After the time limit has expired, you<br>"
						+ "will have to resubmit the request for a password reset.<br><br>"
						+ "<a class='boxed-btn blank2' style='display: inline-block; text-align: center; line-height: 50px; font-size: 14px; border-radius: 4px; color: #fff; background-color: #ee2d50; width: 180px; height: 50px; font-weight: 600; border: 1px solid #ee2d50; text-transform: uppercase; text-decoration: none;' href='"
						+ emailCallBackUrl + "?email="+encodeEmail+"&token="+token+"&data="+encodeData+"'>Change Password</a>";
				helper.setSubject("Reset Password");
				helper.setText(content, true); // set to html
				helper.setFrom(env.getProperty("spring.mail.username"));
				helper.setTo(customer.getEmail());
				//helper.addCc(env.getProperty("mailToCc"));
				emailSender.send(message);
				System.out.println("Email Sending Done...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	@Override
	public void updatePassword(String email, String password) {
		customerRepository.changePassword(email, password);
	}

	@Override
	public Customer findCustomerByPhone(String phone) {
		return customerRepository.checkCustomerPhone(phone);
	}

	@Override
	public void updateCustomerById(Long id, String name, String address, String gender, String phone, String pinCode) {
		customerRepository.updateMyCustomer(id, name, address, gender, phone, pinCode);
	}

	@Override
	public Long getCustomerId(String customerEmail) {
		return customerRepository.findCustomerId(customerEmail);
	}

}
