package com.general.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.general.stores.entity.Product;

public interface ProductAdmRepository extends JpaRepository<Product, Long> {
}
