package com.harry.springbootmall.dao;

import com.harry.springbootmall.dto.UserRegisterRequest;
import com.harry.springbootmall.model.User;

public interface UserDao {
    User getUserByEmail(String email);
    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);

}
