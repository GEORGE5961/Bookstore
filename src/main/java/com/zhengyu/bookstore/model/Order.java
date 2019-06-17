package com.zhengyu.bookstore.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false)
	private Date date;

	@Column(nullable = false)
	private String status;

	@ManyToOne()
	@JoinColumn(name = "userid")
	private User user;

	@OneToMany()
	//@JoinColumn(name = "id")
	//@OrderBy("id asc")
	private Set<Orderitem> orderitems = new HashSet<Orderitem>();

	public Order() {
	}

	public Order(User user, Date date, String status) {
		this.user = user;
		this.date = date;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Orderitem> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(Set<Orderitem> orderitems) {
		this.orderitems = orderitems;
	}
}
