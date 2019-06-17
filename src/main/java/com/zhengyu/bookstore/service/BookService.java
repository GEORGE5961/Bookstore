package com.zhengyu.bookstore.service;

import com.zhengyu.bookstore.model.Book;

import java.util.List;

public interface BookService {
    Book insert(Book book);

    boolean deleteBook(int id);

    void updateBook(Book book);

    Book getBookById(int id);

    List<Book> getAllBooks();

    List<Book> getSomeBooks(String type);

    List<Book> searchBooks(String search);
}
