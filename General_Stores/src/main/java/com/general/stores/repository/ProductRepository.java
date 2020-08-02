package com.general.stores.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.general.stores.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "select p from Product p where p.code=?1")
	Product findProductByCode(String code);

	@Query(value = "select distinct p from Product p where locate(?1, p.name)>0 or locate(?1, p.price)>0 or p.price<=?1")
	List<Product> findSearchedProducts(String name);

	@Query(value = "select p from Product p where p.active=true order by p.createDate desc")
	List<Product> findAllActiveProducts();
	
	@Query(value = "select p from Product p order by p.createDate desc")
	List<Product> findProducts(Pageable pageable);

	@Modifying
	@Query(value = "update Product p set p.name =:name,p.description =:description,p.image =:image,p.mrpPrice =:mrpPrice,p.price =:price,p.active =:active where p.code =:code")
	void updateProduct(@Param("name") String name, @Param("description") String description, @Param("image") String imageData, 
	@Param("price") double price, @Param("mrpPrice") double mrpPrice, @Param("active") boolean active, String code);
}
