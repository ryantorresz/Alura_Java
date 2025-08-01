package com.example.produto_api.service;

import com.example.produto_api.model.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    // Simulando um banco de dados com uma lista
    private List<Product> products = Arrays.asList(
            new Product(1L, "Notebook", 2500.00),
            new Product(2L, "Smartphone", 1500.00),
            new Product(3L, "Tablet", 800.00)
    );

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}