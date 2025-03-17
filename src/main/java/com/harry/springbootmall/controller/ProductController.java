package com.harry.springbootmall.controller;

import com.harry.springbootmall.dto.ProductRequest;
import com.harry.springbootmall.model.Product;
import com.harry.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);

        if (product != null){
            return ResponseEntity.ok(product);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer ProductId = productService.createProduct(productRequest);
        Product product = productService.getProductById(ProductId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
