package com.example.javaCrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.javaCrud.mapper.UserMapper;
import com.example.javaCrud.model.UserModel;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    public int insert(UserModel model) {
        return userMapper.insert(model);
    }
    
    public List<UserModel> selectAll() {
        return userMapper.selectAll();
    }
}
