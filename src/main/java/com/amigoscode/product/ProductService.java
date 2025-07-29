package com.amigoscode.product;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByID(UUID uuid) {
        return productRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException(uuid + " is not found"));
    }
}
