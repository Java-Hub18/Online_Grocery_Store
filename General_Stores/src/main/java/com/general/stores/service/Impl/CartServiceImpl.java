package com.general.stores.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.general.stores.entity.Cart;
import com.general.stores.entity.Customer;
import com.general.stores.repository.CartRepository;
import com.general.stores.service.CartService;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public List<Cart> getCartItemsByCustomerId(Customer customer) {
		return cartRepository.findCartItemsByCustomer(customer);
	}

	@Override
	public void removeCartItems(Customer customer, Long id) {
		cartRepository.deleteCartItems(customer, id);
	}

	@Override
	public void removeCartItem(Long id) {
		cartRepository.deleteCartItem(id);
	}

	@Override
	public void saveCartItems(List<Cart> cartsItems) {
		cartRepository.saveAll(cartsItems);
	}

	@Override
	public void saveCart(Cart cart) {
		cartRepository.save(cart);
	}

}
