package com.capgem.pavani.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="Ratings")
public class Rating {
    @Id
    @Column(name="rating_id")
    private int ratingId;
    @Column(name="rating")
    private int rating;
    @Column(name="review")
    private String review;

    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    
//    @ManyToOne
//    @JoinColumn(name = "customer_id") // Ensure the column name is correct
//    private Customer customer;

	public Rating() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rating(int ratingId, int rating, String review, Order order, Restaurant restaurant) {
		super();
		this.ratingId = ratingId;
		this.rating = rating;
		this.review = review;
		this.order = order;
		this.restaurant = restaurant;
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "Rating [ratingId=" + ratingId + ", rating=" + rating + ", review=" + review + ", order=" + order
				+ ", restaurant=" + restaurant + "]";
	}

    // Getters, setters, constructors, and toString
}

