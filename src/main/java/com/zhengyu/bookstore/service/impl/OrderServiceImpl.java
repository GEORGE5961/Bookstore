package com.zhengyu.bookstore.service.impl;

import com.zhengyu.bookstore.model.*;
import com.zhengyu.bookstore.repository.OrderRepository;
import com.zhengyu.bookstore.repository.OrderitemRepository;
import com.zhengyu.bookstore.service.BookService;
import com.zhengyu.bookstore.service.OrderService;
import com.zhengyu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderitemRepository orderitemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Order insert(Order order, int userid) {

        //插入前先看看这个id在数据库中是否存在
        //存在则不在插入
        User tmpUser= userService.getUserById(userid);
        if(tmpUser==null){
            //System.out.print("fuck");
            return null;
        }
        order.setUser(tmpUser);
        try {
            return orderRepository.saveAndFlush(order);
        }catch (Exception e){
            return null;
        }
    }

    public boolean deleteOrder(int id) {

        try {
            orderRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id);
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Orderitem> getCart(int id) {
        List<Order> orders = getAllOrders();
        Order order = null;
        for (int i=0;i<orders.size(); i++) {
            order = orders.get(i);
            if (order.getUser().getId() == id && order.getStatus().equals("notPaid")){
                List<Orderitem> returnOrderitems = new ArrayList<Orderitem>(order.getOrderitems());
                return returnOrderitems;
            }
        }
        return new ArrayList<Orderitem>();

    }

    public void addToCart(int uid, int bid){
        List<Order> orders = getAllOrders();
        Order order;
        for (int i=0;i<orders.size();i++) {
            order = orders.get(i);
            if (order.getUser().getId()==uid && order.getStatus().equals("notPaid")) {
                Set<Orderitem> orderitems = order.getOrderitems();
                Iterator<Orderitem> iterator = orderitems.iterator();
                while(iterator.hasNext()){
                    Orderitem orderitem = (Orderitem)iterator.next();
                    if (orderitem.getBookid() == bid) {
                        orderitem.setAmount(orderitem.getAmount()+1);
                        orderitemRepository.save(orderitem);
                        return;
                    }
                }
                Orderitem orderitem = new Orderitem(order,bid,1,0);
                orderitemRepository.save(orderitem);
                return;
            }
        }
        User user = userService.getUserById(uid);
        order = new Order(user, new Date(new java.util.Date().getTime()),"notPaid");
        orderRepository.save(order);
        orders = getAllOrders();
        for (int i=orders.size()-1;i>-1;i--) {
            order = orders.get(i);
            if (order.getUser().getId()==uid && order.getStatus().equals("notPaid")) {
                Orderitem orderitem = new Orderitem(order,bid,1,0);
                orderitemRepository.save(orderitem);
                return;
            }
        }
    }

    public void checkOut(int uid, List<Integer> itemIds) {
        List<Order> orders = getAllOrders();
        Order order;
        for (int i=0;i<orders.size();i++) {
            order = orders.get(i);
            if (order.getUser().getId()==uid && order.getStatus().equals("notPaid")) {
                order.setStatus("isPaid");
                order.setDate(new Date(new java.util.Date().getTime()));// to debug
                updateOrder(order);

                Set<Orderitem> orderitems = order.getOrderitems();
                Set<Orderitem> newOrderitems = new HashSet<Orderitem>();
                Iterator<Orderitem> iterator = orderitems.iterator();
                while(iterator.hasNext()){
                    Orderitem orderitem = (Orderitem)iterator.next();
                    if (itemIds.contains(orderitem.getId())) {
                        Book book = bookService.getBookById(orderitem.getBookid());
                        orderitem.setPrice(book.getPrice());
                        orderitemRepository.save(orderitem);
                    }
                    else {
                        newOrderitems.add(orderitem);
                    }
                }
                if (newOrderitems.size()>0) {
                    User user = userService.getUserById(uid);
                    Order newOrder = new Order(user, new Date(new java.util.Date().getTime()), "notPaid");
                    orderRepository.save(newOrder);

                    int orderid=-1;
                    orders = getAllOrders();

                    for (int j=orders.size()-1;j>-1;j--) {
                        order = orders.get(j);
                        if (order.getUser().getId()==uid && order.getStatus().equals("notPaid")) {
                            orderid = order.getId();
                            break;
                        }
                    }

                    for (Orderitem orderitem:newOrderitems) {
                        orderitem.setOrder(order);
                    }
                }
                return;
            }
        }
    }

    public List<Stat> getStats(String user, int userid, Date startDate, Date endDate, String book, String booktype, int bookid) {
        List<Orderitem> orderitems = orderitemRepository.findAll();
        List<Book> books = bookService.getAllBooks();
        List<Stat> stats = new ArrayList<Stat>();

        for (Orderitem orderitem:orderitems) {
            // indicate this OrderItem has not been paid
            if (orderitem.getPrice()==0) {
                continue;
            }
            Order thisOrder =getOrderById(orderitem.getOrder().getId());
            Book thisBook = bookService.getBookById(orderitem.getBookid());

            if (user!=null && user.equals("specific")) {
                int thisUserid = thisOrder.getUser().getId();
                if (userid != thisUserid) {
                    continue;
                }
            }

            Date date = thisOrder.getDate();
            if (startDate!=null && startDate.after(date)) {
                continue;
            }
            if (endDate!=null && endDate.before(date)) {
                continue;
            }

            if (book!=null && book.equals("specific")) {
                if (orderitem.getBookid()!=bookid) {
                    continue;
                }
            }
            if (book!=null && book.equals("categories")) {
                if (!booktype.equals("all") && !thisBook.getType().equals(booktype)) {
                    continue;
                }
            }

            // satisfy every condition, thus add to the result
            int id = orderitem.getBookid();
            boolean found = false;
            for (Stat thisStat:stats) {
                if (thisStat.getId() == id) {
                    thisStat.setAmount(thisStat.getAmount()+orderitem.getAmount());
                    thisStat.setTotal(thisStat.getTotal()+orderitem.getPrice()*orderitem.getAmount());
                    thisStat.setUnitPrice(thisStat.getTotal()/thisStat.getAmount());
                    System.out.println("<!>"+ thisStat.getId()+", "+thisStat.getName()+", "+thisStat.getAmount()+", "+thisStat.getTotal());
                    found = true;
                    break;
                }
            }
            if (found) continue;
            String name = bookService.getBookById(orderitem.getBookid()).getTitle();
            int amount = orderitem.getAmount(), unitPrice = orderitem.getPrice(), total = unitPrice*amount;
            Stat stat = new Stat(id, name, amount, unitPrice, total);
            stats.add(stat);
        }

        return stats;
    }
}
