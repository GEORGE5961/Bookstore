package com.zhengyu.bookstore.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private String publisher;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String intro;

    public Book() {
    }

    public Book(String title, String author, int price, String publisher, Date date, String type, String intro) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.publisher = publisher;
        this.date = date;
        this.type = type;
        this.intro = intro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }


}
