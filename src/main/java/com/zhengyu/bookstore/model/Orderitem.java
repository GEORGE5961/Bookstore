package com.zhengyu.bookstore.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "orderitems")
public class Orderitem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false)
	private int bookid;

	@Column(nullable = false)
	private int amount;

	@Column(nullable = false)
	private int price;

	@ManyToOne()
	@JoinColumn(name = "orderid")
	private Order order;

	public Orderitem() {
	}

	public Orderitem(Order order, int bookid, int amount, int price) {
		this.order = order;
		this.bookid = bookid;
		this.amount = amount;
		this.price = price;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
