package com.example.carculator.Shop.Repository;

import com.example.carculator.Shop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
