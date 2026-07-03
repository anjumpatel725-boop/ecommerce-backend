package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "https://ecommerce-backend-production-075f.up.railway.app")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public Product add(@RequestBody Product p) {
        return productService.save(p);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        return productService.update(id, p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    // ✅ COUNT API (FIXED)
    @GetMapping("/count")
    public Long getProductCount() {
        return productRepository.count();
    }

    // ✅ TOTAL VALUE API (FIXED)
    @GetMapping("/total-value")
    public Double getTotalProductValue() {
        return productRepository.findAll()
                .stream()
                .mapToDouble(p -> p.getPrice().doubleValue() * p.getStockQuantity())
                .sum();
    }

    @GetMapping("/filter")
    public List<Product> filter(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice
    ) {
        return productService.filter(keyword, minPrice, maxPrice);
    }
    @GetMapping("/search")
public List<Product> search(@RequestParam String keyword) {
    return productService.filter(keyword, null, null);
}
}