package com.harry.springbootmall.service;

import com.harry.springbootmall.dto.ProductRequest;
import com.harry.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);

}
