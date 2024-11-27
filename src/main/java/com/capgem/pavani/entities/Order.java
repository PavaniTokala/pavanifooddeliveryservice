package com.capgem.pavani.entities;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="Orders")
public class Order {
    @Id
    @Column(name="order_id")
    private int orderId;
    
    @Column(name="order_date")
    private Date orderDate;
    @Column(name="order_status")
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "delivery_driver_id")
    @JsonIgnore
    private DeliveryDriver deliveryDriver;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order")
    @JsonIgnore
    private Rating rating;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int orderId, Date orderDate, String orderStatus, Customer customer, Restaurant restaurant,
			DeliveryDriver deliveryDriver, List<OrderItem> orderItems, Rating rating) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.customer = customer;
		this.restaurant = restaurant;
		this.deliveryDriver = deliveryDriver;
		this.orderItems = orderItems;
		this.rating = rating;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public DeliveryDriver getDeliveryDriver() {
		return deliveryDriver;
	}

	public void setDeliveryDriver(DeliveryDriver deliveryDriver) {
		this.deliveryDriver = deliveryDriver;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus + ", customer="
				+ customer + ", restaurant=" + restaurant + "]";
	}

    // Getters, setters, constructors, and toString
}

