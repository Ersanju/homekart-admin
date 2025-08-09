package com.homekart.homekart_admin.controller;

import com.homekart.homekart_admin.dto.ProductReviewRequest;
import com.homekart.homekart_admin.model.Product;
import com.homekart.homekart_admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add-products")
    public String addMultipleProducts(@RequestBody List<Product> products) throws ExecutionException, InterruptedException {
        return productService.addMultipleProducts(products);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryId) {
        try {
            List<Product> products = productService.getProductsByCategory(categoryId);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/add-reviews")
    public String addMultipleReviews(@RequestBody ProductReviewRequest request)
            throws ExecutionException, InterruptedException {
        return productService.addReviewsToProduct(request.getProductId(), request.getReviews());
    }
}


