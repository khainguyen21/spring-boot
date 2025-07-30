package com.amigoscode.product;

import com.amigoscode.exception.ResourceNotFound;
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
                .orElseThrow(() -> new ResourceNotFound(
                        uuid + " is not found"
                ));
    }

    public void deleteProductByID(UUID id) {
        boolean exists = productRepository.existsById(id);

        if (!exists) {
            throw new ResourceNotFound(
                    "Product with " + id + " is not found"
            );
        }

        productRepository.deleteById(id);
    }

    public UUID saveNewProduct(NewProductRequest request) {
        UUID id = UUID.randomUUID();
        Product newProduct = new Product(
                id,
                request.name(),
                request.description(),
                request.price(),
                request.imageUrl(),
                request.stockLevel()
        );
        productRepository.save(newProduct);
        return id;
    }
}
