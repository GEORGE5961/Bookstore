package com.zhengyu.bookstore.service.impl;

import com.zhengyu.bookstore.model.User;
import com.zhengyu.bookstore.repository.UserRepository;
import com.zhengyu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User insert(User user) {

        try {
            //插入前先看看这个id在数据库中是否存在
            //存在则不在插入
            User tmpUser=userRepository.findById(user.getId());
            if(tmpUser!=null)
                return null;
            return userRepository.saveAndFlush(user);
        }
        catch (Exception e){
            return null;
        }
    }

    public boolean deleteUser(int id) {

        try {
            userRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }

    public void updateUser(User user) {
        userRepository.updateById(user.getUsername(),user.getId());
    }

    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
