package com.zhengyu.bookstore.service.impl;

import com.zhengyu.bookstore.model.Book;
import com.zhengyu.bookstore.repository.BookRepository;
import com.zhengyu.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book insert(Book book) {

        try {
            //插入前先看看这个id在数据库中是否存在
            //存在则不在插入
            Book tmpBook=bookRepository.findById(book.getId());
            if(tmpBook!=null)
                return null;
            return bookRepository.saveAndFlush(book);
        }
        catch (Exception e){
            return null;
        }
    }

    public boolean deleteBook(int id) {

        try {
            bookRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getSomeBooks(String type) {
        List<Book> books = new ArrayList<Book>();
        for (Book book:getAllBooks()) {
            if (book.getType().equals(type)) {
                books.add(book);
                System.out.println("<!> "+book.getTitle());
            }
        }
        return books;
    }

    public List<Book> searchBooks(String search) {
        List<Book> books = new ArrayList<Book>();
        search = search.toLowerCase();
        for (Book book:getAllBooks()) {
            if (book.getTitle().toLowerCase().contains(search)) {
                books.add(book);
            }
        }
        return books;
    }
}
