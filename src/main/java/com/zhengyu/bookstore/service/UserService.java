package com.zhengyu.bookstore.service;

import com.zhengyu.bookstore.model.Orderitem;
import com.zhengyu.bookstore.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;


public interface UserService {

    User insert(User user);

    boolean deleteUser(int id);

    void updateUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    int verifyLogin(String username, String password);
    int hasLogin(HttpSession session);
    int isAdmin(HttpSession session);
    int signUp(String username, String password);
    int belongTo(HttpSession session, Orderitem orderitem);

    void editProfile(int userid, String password, String intro);
}
