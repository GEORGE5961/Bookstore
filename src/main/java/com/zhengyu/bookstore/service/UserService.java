package com.zhengyu.bookstore.service;

import com.zhengyu.bookstore.model.User;

import java.util.List;

public interface UserService {

    User insert(User user);

    boolean deleteUser(int id);

    void updateUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

}
