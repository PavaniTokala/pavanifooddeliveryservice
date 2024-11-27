package com.capgem.pavani.entities;

import jakarta.persistence.*;

@Entity
@Table(name="orders_coupons")
public class OrdersCoupons {
    @Id
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

	public OrdersCoupons() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdersCoupons(int id, Order order, Coupon coupon) {
		super();
		this.id = id;
		this.order = order;
		this.coupon = coupon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	@Override
	public String toString() {
		return "OrdersCoupons [id=" + id + ", order=" + order + ", coupon=" + coupon + "]";
	}

    // Getters, setters, constructors, and toString
}
