package com.zhengyu.bookstore.service;

import com.zhengyu.bookstore.model.Order;
import com.zhengyu.bookstore.model.Orderitem;
import com.zhengyu.bookstore.model.Stat;

import java.sql.Date;
import java.util.List;

public interface OrderService {

    Order insert(Order order, int userid);

    boolean deleteOrder(int id);

    void updateOrder(Order order);

    Order getOrderById(int id);

    List<Order> getAllOrders();

    // cart

    List<Orderitem> getCart(int id);

    void addToCart(int uid, int bid);

    void checkOut(int uid, List<Integer> itemIds);

    List<Stat> getStats(String user, int userid, Date startDate, Date endDate, String book, String booktype, int bookid);
}
