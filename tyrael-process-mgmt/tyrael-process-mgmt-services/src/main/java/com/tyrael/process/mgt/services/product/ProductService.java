package com.tyrael.process.mgt.services.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyrael.process.mgt.models.product.Product;

public interface ProductService extends JpaRepository<Product, Long> {

}
