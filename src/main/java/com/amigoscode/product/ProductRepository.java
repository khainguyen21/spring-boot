package com.amigoscode.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;



// This interface will give us the ability to perform CRUD operation
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
