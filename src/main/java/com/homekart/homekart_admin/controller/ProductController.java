package com.homekart.homekart_admin.controller;

import com.homekart.homekart_admin.model.Product;
import com.homekart.homekart_admin.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService service) {
        this.productService = service;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            String timestamp = productService.addProduct(product);
            return ResponseEntity.ok("Product saved at: " + timestamp);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
