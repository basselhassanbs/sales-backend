package com.basel.sales.clone.repository;

import com.basel.sales.clone.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
