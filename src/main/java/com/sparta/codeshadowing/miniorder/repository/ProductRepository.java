package com.sparta.codeshadowing.miniorder.repository;

import com.sparta.codeshadowing.miniorder.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
