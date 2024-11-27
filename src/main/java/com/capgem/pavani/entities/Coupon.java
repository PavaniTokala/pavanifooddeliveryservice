package com.capgem.pavani.entities;


import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name="Coupons")
public class Coupon {
    @Id
    @Column(name="coupon_id")
    private int couponId;

    @Column(name="coupon_code",unique = true)
    private String couponCode;
    @Column(name="discount_amount")
    private double discountAmount;
    @Column(name="expiry_date")
    private Date expiryDate;

    @OneToMany(mappedBy = "coupon")
    private List<OrdersCoupons> ordersCoupons;

	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coupon(int couponId, String couponCode, double discountAmount, Date expiryDate,
			List<OrdersCoupons> ordersCoupons) {
		super();
		this.couponId = couponId;
		this.couponCode = couponCode;
		this.discountAmount = discountAmount;
		this.expiryDate = expiryDate;
		this.ordersCoupons = ordersCoupons;
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public List<OrdersCoupons> getOrdersCoupons() {
		return ordersCoupons;
	}

	public void setOrdersCoupons(List<OrdersCoupons> ordersCoupons) {
		this.ordersCoupons = ordersCoupons;
	}

	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", couponCode=" + couponCode + ", discountAmount=" + discountAmount
				+ ", expiryDate=" + expiryDate + "]";
	}

    // Getters, setters, constructors, and toString
}

