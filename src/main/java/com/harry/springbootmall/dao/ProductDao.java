package com.harry.springbootmall.dao;

import com.harry.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
