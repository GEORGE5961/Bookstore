package com.zhengyu.bookstore.Controller;


import com.zhengyu.bookstore.model.Orderitem;
import com.zhengyu.bookstore.model.User;
import com.zhengyu.bookstore.service.OrderitemService;
import com.zhengyu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitems")
public class OrderitemController {

    @Autowired
    private OrderitemService orderitemService;

    @ResponseBody
    @PostMapping("/addOrderitem")
    public Orderitem addOrderitem(Orderitem orderitem, int orderId)
    {
        //System.out.println("first statement.");
        return orderitemService.insert(orderitem, orderId);
    }

    @ResponseBody
    @GetMapping("/getAllOrderitems")
    public List<Orderitem> getAllOrderitems()
    {
        return orderitemService.getAllOrderitems();
    }

    @ResponseBody
    @PostMapping("/deleteByOrderitemId")
    public boolean deleteByOrderitemId(int orderitemId){
        return orderitemService.deleteOrderitem(orderitemId);
    }

    @ResponseBody
    @PostMapping("/updateOrderitem")
    public void updateOrderitem(Orderitem orderitem)
    {
        orderitemService.updateOrderitem(orderitem);
    }


}
