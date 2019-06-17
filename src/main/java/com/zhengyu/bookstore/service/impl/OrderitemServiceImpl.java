package com.zhengyu.bookstore.service.impl;


import com.zhengyu.bookstore.model.Order;
import com.zhengyu.bookstore.model.Orderitem;
import com.zhengyu.bookstore.model.User;
import com.zhengyu.bookstore.repository.OrderitemRepository;
import com.zhengyu.bookstore.repository.UserRepository;
import com.zhengyu.bookstore.service.OrderService;
import com.zhengyu.bookstore.service.OrderitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderitemServiceImpl implements OrderitemService {

    @Autowired
    private OrderitemRepository orderitemRepository;

    @Autowired
    private OrderService orderService;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public Orderitem insert(Orderitem orderitem, int orderid) {

        //插入前先看看这个id在数据库中是否存在
        //存在则不在插入
        Order tmpOrder= orderService.getOrderById(orderid);
        if(tmpOrder==null){
            //System.out.print("fuck");
            return null;
        }
        orderitem.setOrder(tmpOrder);
        //orderitemRepository.save(orderitem);
        //throw new RuntimeException("抛异常了");
        try {
            return orderitemRepository.saveAndFlush(orderitem);
        }catch (Exception e){
            return null;
        }
    }

    public boolean deleteOrderitem(int id) {

        try {
            orderitemRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }

    public void updateOrderitem(Orderitem orderitem) {
        orderitemRepository.save(orderitem);
    }

    public Orderitem getOrderitemById(int id) {
        return orderitemRepository.findById(id);
    }

    public List<Orderitem> getAllOrderitems() {
        return orderitemRepository.findAll();
    }
}
