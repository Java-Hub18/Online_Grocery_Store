package com.general.stores.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.general.stores.entity.Product;
import com.general.stores.exception.MyResourceNotFoundException;
import com.general.stores.service.ProductAdmService;
import com.general.stores.service.ProductService;

@Controller
@RequestMapping("/admin/product")
public class ProductAdmController {

	@Value("${upoadDir}")
	private String uploadFolder;

	@Autowired
	private ProductService productService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductAdmService productAdmSer;

	@GetMapping("/add")
	public String addProduct(HttpSession session) {
		String aemail = (String) session.getAttribute("aemail");
		if(aemail == null) {
			return "redirect:/home";
		}
		return "add_product";
	}

	@GetMapping("/edit/{code}")
	public String editProduct(@PathVariable("code") String code, Product product, Model map, HttpSession session) {
		String aemail = (String) session.getAttribute("aemail");
		if(aemail == null) {
			return "redirect:/home";
		}
		product = productService.getProductByCode(code);
		if (product != null) {
			map.addAttribute("code", code);
			map.addAttribute("product", product);
			return "edit_product";
		}
		map.addAttribute("error", "Product Not Found With This Code.");
		return "edit_product";
	}

	@PostMapping("/saveProduct")
	public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("name") String name,
			@RequestParam("active") String status, @RequestParam("price") double price, @RequestParam("mrp_price") double mrpPrice,
			@RequestParam("description") String description, Model model, HttpServletRequest request,
			final @RequestParam("image") MultipartFile file) {
		try {
			// String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName,
						HttpStatus.BAD_REQUEST);
			}
			String[] names = name.split(",");
			String code = "";
			Long count = productAdmSer.countProduct();
			log.info("count:: " + count);
			if (count < 9) {
				code = "P0" + (count + 1);
			} else {
				code = "P" + (count + 1);
				log.info("lastValue : " + code);
			}
			String[] descriptions = description.split(",");
			String[] statuses = status.split(",");
			boolean active = Boolean.valueOf(statuses[0]);
			Date createDate = new Date();
			log.info("Name: " + names[0] + " " + filePath);
			log.info("description: " + descriptions[0]);
			log.info("Code: " + code);
			log.info("status: " + statuses[0]);
			log.info("price: " + price);
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			String imageData = uploadFolder + File.separator + fileName;
			Product product = new Product();
			product.setName(names[0]);
			product.setCode(code);
			product.setImage(imageData);
			product.setMrpPrice(mrpPrice);
			product.setPrice(price);
			product.setDescription(descriptions[0]);
			product.setActive(active);
			product.setCreateDate(createDate);
			boolean flag = productService.saveProduct(product);
			if (flag) {
				log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
				return new ResponseEntity<>("Product Added Successfully.", HttpStatus.OK);
			}
			return new ResponseEntity<>("Oops! Something Went Wrong.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/view")
	public String view(Model map, HttpServletRequest request,HttpSession session) {
		String aemail = (String) session.getAttribute("aemail");
		if(aemail == null) {
			return "redirect:/home";
		}
		List<Product> productList = productService.getAllActiveProducts();
		map.addAttribute("product", productList);
		return "admin_product_view";
	}

	@GetMapping("/delete/{id}/{fileName}")
	public String deleteProduct(@PathVariable("id") Long pid, @PathVariable("fileName") String fileName,
			HttpServletRequest request, HttpSession session, RedirectAttributes rda) {
		String aemail = (String) session.getAttribute("aemail");
		if(aemail == null) {
			return "redirect:/home";
		}
		File file =null;
		try {
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			if(fileName == null || fileName.isEmpty()) {
				fileName = "1x1.jpg";
			}
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + fileName);
			log.info("filePath: " + filePath);
			if (fileName == null || fileName.contains("..")) {
				rda.addFlashAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return "redirect:/admin/product/view";
			}
			file = new File(filePath);
			if(file.exists()) {
				file.delete();
			}
			rda.addFlashAttribute("delete", "delete");
			productAdmSer.deleteProduct(pid);
		} catch (Exception e) {
			rda.addFlashAttribute("error", "e");
			e.printStackTrace();
		}
		return "redirect:/admin/product/view";
	}
	
	@GetMapping("/delete/{id}/")
	public String deleteTestProduct(@PathVariable("id") Long pid,
			HttpServletRequest request, HttpSession session, RedirectAttributes rda) {
		try {
			log.info("In Test Delete.");
			String aemail = (String) session.getAttribute("aemail");
			if(aemail == null) {
				return "redirect:/home";
			}
			rda.addFlashAttribute("delete", "delete");
			productAdmSer.deleteProduct(pid);
		} catch (Exception e) {
			rda.addFlashAttribute("error", "e");
			e.printStackTrace();
		}
		return "redirect:/admin/product/view";
	}

	@PostMapping("/editProduct")
	public @ResponseBody ResponseEntity<?> updateProduct(@RequestParam("name") String name,
			@RequestParam("active") String status, @RequestParam("price") double price, @RequestParam("mrp_price") double mrpPrice,
			@RequestParam("description") String description, Model model, HttpServletRequest request, HttpSession session,
			final @RequestParam("image") MultipartFile file) {
		try {
			String imageData ="";
			if(file.isEmpty()) {
				imageData = (String) session.getAttribute("imageData");
				//String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
				log.info("In If Empty File:: "+imageData);
			} else {
				log.info("In Else With File");
				String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
				log.info("uploadDirectory:: " + uploadDirectory);
				String fileName = file.getOriginalFilename();
				String filePath = Paths.get(uploadDirectory, fileName).toString();
				log.info("FileName: " + file.getOriginalFilename());
				if (fileName == null || fileName.contains("..")) {
					model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
					return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName,
							HttpStatus.BAD_REQUEST);
				}
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
				imageData = uploadFolder + File.separator + fileName;
			}
			String code = (String) session.getAttribute("code");
			String[] names = name.split(",");
			String[] descriptions = description.split(",");
			String[] statuses = status.split(",");
			boolean active = Boolean.valueOf(statuses[0]);
			log.info("Name: " + names[0] + " " + code);
			log.info("description: " + descriptions[0]);
			log.info("status: " + statuses[0]);
			log.info("price: " + price);
			productService.updateProductByCode(names[0], descriptions[0], imageData, mrpPrice, price, active, code);
			log.info("Product Updated With Code : "+code);
			session.removeAttribute("code");
			session.removeAttribute("imageData");
			return new ResponseEntity<>("Product Updated Successfully.", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>("Oops! Something Went Wrong.", HttpStatus.BAD_REQUEST);
		}
	}

	@ExceptionHandler(MyResourceNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(MyResourceNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
