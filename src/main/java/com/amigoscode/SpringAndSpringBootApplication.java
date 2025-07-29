package com.amigoscode;

import com.amigoscode.product.Product;
import com.amigoscode.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootApplication
public class SpringAndSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                SpringAndSpringBootApplication.class,
                args
        );
    }

    @Bean
    public CommandLineRunner commandLineRunner(ProductRepository repository) {
        return args -> {
            Product product = new Product();
            product.setName("Macbook Pro");
            product.setDescription("Macbook Pro 13 inch");
            product.setPrice(new BigDecimal("1300"));
            product.setStockLevel(100);
            product.setId(UUID.fromString("46b16608-134c-497c-82b8-10962b9c6537"));
            repository.save(product);
        };
    }

}
