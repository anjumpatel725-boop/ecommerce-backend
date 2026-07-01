package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product save(Product p) {
        return productRepository.save(p);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product update(Long id, Product p) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(p.getName());
        existing.setDescription(p.getDescription());
        existing.setPrice(p.getPrice());
        existing.setStockQuantity(p.getStockQuantity());
        existing.setImageUrl(p.getImageUrl());

        return productRepository.save(existing);
    }

    public List<Product> filter(
            String keyword,
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {
        return productRepository.searchProducts(
                keyword,
                minPrice,
                maxPrice
        );
    }
}