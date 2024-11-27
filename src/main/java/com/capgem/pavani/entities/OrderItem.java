package com.capgem.pavani.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @Column(name="order_item_id")
    private int orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private MenuItem menuItem;

    @Column(name="quantity")
    private int quantity;

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItem(int orderItemId, Order order, MenuItem menuItem, int quantity) {
		super();
		this.orderItemId = orderItemId;
		this.order = order;
		this.menuItem = menuItem;
		this.quantity = quantity;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", order=" + order + ", menuItem=" + menuItem + ", quantity="
				+ quantity + "]";
	}

    // Getters, setters, constructors, and toString
}

