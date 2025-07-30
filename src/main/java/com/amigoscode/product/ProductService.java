package com.amigoscode.product;

import com.amigoscode.exception.ResourceNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper())
                .collect(Collectors.toList());
    }


    public ProductResponse getProductByID(UUID uuid) {
        return productRepository.findById(uuid)
                .map(productMapper())
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
    private Function<Product, ProductResponse> productMapper() {
        return product -> new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImageUrl(),
                product.getStockLevel(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getDeletedAt()
        );
    }

    public void updateProduct(UUID uuid, UpdateProductRequest request) {
        Product product = productRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFound(uuid + " is not found")
                );

        if (request.name() != null && !request.name().equals(product.getName())) {
            product.setName(request.name());
        }
        if (request.description() != null && !request.description().equals(product.getDescription())) {
            product.setDescription(request.description());
        }
        if (request.price() != null && !request.price().equals(product.getPrice())) {
            product.setPrice(request.price());
        }
        if (request.imageUrl() != null && !request.imageUrl().equals(product.getImageUrl())) {
            product.setImageUrl(request.imageUrl());
        }
        if (request.stockLevel() != null && !request.stockLevel().equals(product.getStockLevel())) {
            product.setStockLevel(request.stockLevel());
        }
        productRepository.save(product);
    }
}
