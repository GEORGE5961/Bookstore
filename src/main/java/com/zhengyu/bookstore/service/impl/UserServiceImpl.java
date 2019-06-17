package com.zhengyu.bookstore.service.impl;

import com.zhengyu.bookstore.model.Orderitem;
import com.zhengyu.bookstore.model.User;
import com.zhengyu.bookstore.repository.OrderRepository;
import com.zhengyu.bookstore.repository.UserRepository;
import com.zhengyu.bookstore.service.OrderService;
import com.zhengyu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderService orderService;

    @Transactional(propagation = Propagation.NESTED)
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
        userRepository.save(user);
    }

    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * verify login
     */
    public int verifyLogin(String username, String password) {
        List<User> userList = getAllUsers();

        for (int i=0; i< userList.size(); i++) {
            User user = userList.get(i);
            if (user.getUsername().equals(username)){
                if (user.getPassword().equals(password)){
                    return user.getId();
                }
                else {
                    return -1;
                }
            }
        }
        return -1;
    }

    public int hasLogin(HttpSession session) {
        Object useridObject = session.getAttribute("id");
        if (useridObject == null){
            return -1;
        }
        int userid = Integer.parseInt(useridObject.toString());
        return userid;
    }

    public int isAdmin(HttpSession session) {
        Object userRoleObject = session.getAttribute("role");
        if (userRoleObject != null && userRoleObject.equals("admin")) {
            return 1;
        }
        return -1;

    }

    public int signUp(String username, String password) {
        List<User> userList = getAllUsers();

        for (int i=0; i< userList.size(); i++) {
            User user = userList.get(i);
            if (user.getUsername().equals(username)){
                return -1;
            }
        }
        User user = new User(username, password, "guest", "No introduction yet.");
        userRepository.save(user);
        return verifyLogin(username,password);
    }

    public int belongTo(HttpSession session, Orderitem orderitem) {
        Object useridObject = session.getAttribute("id");
        if (useridObject == null) {
            return -1;
        }
        int userid = Integer.parseInt(useridObject.toString());
        if (userid != orderService.getOrderById(orderitem.getOrder().getId()).getUser().getId() ) {
            if (isAdmin(session)<0)
                return -1;
        }
        return 1;
    }

    public void editProfile(int userid,String password, String intro) {
        User user = getUserById(userid);
        user.setPassword(password);
        user.setIntro(intro);
        userRepository.save(user);

    }
}
