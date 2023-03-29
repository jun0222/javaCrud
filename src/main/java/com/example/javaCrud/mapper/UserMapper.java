package com.example.javaCrud.mapper;

import java.util.List;
import com.example.javaCrud.model.UserModel;

public interface UserMapper {
    int insert(UserModel model);
    List<UserModel> selectAll();
}
