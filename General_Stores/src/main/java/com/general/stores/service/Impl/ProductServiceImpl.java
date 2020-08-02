package com.general.stores.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.general.stores.entity.Product;
import com.general.stores.repository.ProductRepository;
import com.general.stores.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllActiveProducts() {
		return productRepository.findAllActiveProducts();
	}

	@Override
	public List<Product> getProducts(Pageable pageable) {	
		return productRepository.findProducts(pageable);
	}

	@Override
	public Product getProductByCode(String code) {
		return productRepository.findProductByCode(code);
	}

	@Override
	public boolean saveProduct(Product product) {
		boolean flag = false;
		if (product != null) {
			productRepository.save(product);
			flag = true;
			return flag;
		}
		return flag;
	}

	@Override
	public List<Product> searchProducts(String name) {
		return productRepository.findSearchedProducts(name);
	}

	@Override
	public void updateProductByCode(String name, String description, String imageData, double mrpPrice, double price, boolean active, String code) {
		productRepository.updateProduct(name, description, imageData, mrpPrice, price, active, code);
	}

}
