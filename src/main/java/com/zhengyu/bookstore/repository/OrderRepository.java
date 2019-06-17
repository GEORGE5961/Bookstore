package com.zhengyu.bookstore.repository;

import com.zhengyu.bookstore.model.Order;
import com.zhengyu.bookstore.model.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findById(int orderId);
}
