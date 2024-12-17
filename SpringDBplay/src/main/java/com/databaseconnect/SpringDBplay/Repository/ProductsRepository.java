package com.databaseconnect.SpringDBplay.Repository;

import com.databaseconnect.SpringDBplay.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Integer> {
}
