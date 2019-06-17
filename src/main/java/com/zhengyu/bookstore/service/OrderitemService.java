package com.zhengyu.bookstore.service;

import com.zhengyu.bookstore.model.Orderitem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderitemService {

    Orderitem insert(Orderitem orderitem, int orderid);

    boolean deleteOrderitem(int id);

    void updateOrderitem(Orderitem orderitem);

    Orderitem getOrderitemById(int id);

    List<Orderitem> getAllOrderitems();
}
