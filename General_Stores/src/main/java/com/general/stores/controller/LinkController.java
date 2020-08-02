package com.general.stores.controller;

import java.util.List;

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

import com.general.stores.entity.Product;
import com.general.stores.service.ProductAdmService;
import com.general.stores.service.ProductService;

@Controller
public class LinkController {

	@Autowired
	private ProductService productService;

	@Autowired
	ProductAdmService productAdmSer;

	@Value("${product_size}")
	private int size;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = { "/", "/index", "/home", "" })
	public String homePage(Model map) {
		Pageable pageable = PageRequest.of(0, size);
		List<Product> product = productService.getProducts(pageable);
		Long count = productAdmSer.countProduct();
		log.info("count:: " + count);
		map.addAttribute("count", count);
		map.addAttribute("product_size", size);
		map.addAttribute("products", product);
		log.info("Showing Home Page.");
		return "index";
	}

	@GetMapping(value = { "/products/{pageNo}" })
	public String homePages(Model model, @PathVariable("pageNo") int pageNo) {
		Long count = productAdmSer.countProduct();
		log.info("count:: " + count+" pageNo:: "+pageNo);
		if (pageNo <= 0 || count < size) {
			return "redirect:/home";
		}
		Pageable pageable = PageRequest.of(pageNo, size);
		List<Product> product = productService.getProducts(pageable);
		if (!product.isEmpty()) {
			int nextNum = pageNo + 1;
			int backNum = pageNo - 1;
			String nextUrl = "/products/" + nextNum;
			String backUrl ="";
			if(pageNo == 0) {
				backUrl = "/products/" + 0;
			} else {
				backUrl = "/products/" + backNum;
			}
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("nextUrl", nextUrl);
			model.addAttribute("backUrl", backUrl);
			model.addAttribute("products", product);
			log.info("Showing Products, Page No. "+pageNo);
		} else {
			log.info("In else ... There is no products.");
			model.addAttribute("pageNo", 0);
			model.addAttribute("products", product);
		}
		return "index";
	}

	@GetMapping("/contact")
	public String contactPage() {
		return "contact-us";
	}

	@PostMapping("/checkout")
	public String servicesPage() {
		return "checkout";
	}

	@GetMapping("/Add_Product")
	public String addProduct() {
		return "new-product";
	}

	@GetMapping("/about")
	public String aboutPage() {
		return "about-us";
	}

	@GetMapping("/page404")
	public String pageNotFound() {
		return "page404";
	}

}
