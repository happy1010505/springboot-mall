package com.harry.springbootmall.service.impl;

import com.harry.springbootmall.dao.UserDao;
import com.harry.springbootmall.dto.UserRegisterRequest;
import com.harry.springbootmall.model.User;
import com.harry.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

}
