package com.zhengyu.bookstore.repository;

import com.zhengyu.bookstore.model.Orderitem;
import com.zhengyu.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderitemRepository extends JpaRepository<Orderitem,Integer> {

    Orderitem findById(int userId);

}
