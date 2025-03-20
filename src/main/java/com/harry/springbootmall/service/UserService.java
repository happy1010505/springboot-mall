package com.harry.springbootmall.service;

import com.harry.springbootmall.dto.UserLoginRequest;
import com.harry.springbootmall.dto.UserRegisterRequest;
import com.harry.springbootmall.model.Product;
import com.harry.springbootmall.model.User;

public interface UserService {
    User getUserById (Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);
    User login(UserLoginRequest userLoginRequest);
}
