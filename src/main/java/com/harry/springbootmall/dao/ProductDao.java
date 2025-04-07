package com.harry.springbootmall.dao;

import com.harry.springbootmall.constant.ProductCategory;
import com.harry.springbootmall.dto.ProductQueryParams;
import com.harry.springbootmall.dto.ProductRequest;
import com.harry.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    Integer countProduct(ProductQueryParams productQueryParams);
    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void deleteProductById(Integer productId);
    void updateStock(Integer productId,Integer stock);

}
