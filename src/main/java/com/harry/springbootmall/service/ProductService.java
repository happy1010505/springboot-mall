package com.harry.springbootmall.service;

import com.harry.springbootmall.constant.ProductCategory;
import com.harry.springbootmall.dto.ProductQueryParams;
import com.harry.springbootmall.dto.ProductRequest;
import com.harry.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void deleteProductById(Integer productId);

}
