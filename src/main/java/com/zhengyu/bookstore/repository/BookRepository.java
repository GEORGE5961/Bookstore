package com.zhengyu.bookstore.repository;

import com.zhengyu.bookstore.model.Book;
import com.zhengyu.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    Book findById(int bookId);
}
