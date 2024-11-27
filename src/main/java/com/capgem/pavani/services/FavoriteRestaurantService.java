//package com.capgem.harshit.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.capgem.harshit.entities.FavoriteRestaurant;
//import com.capgem.harshit.repositories.FavoriteRestaurantRepository;
//
//import java.util.List;
//
//@Service
//public class FavoriteRestaurantService {
//
//    @Autowired
//    private FavoriteRestaurantRepository favoriteRestaurantRepository;
//
//    public List<FavoriteRestaurant> getFavoritesByCustomerId(int customerId) {
//        return favoriteRestaurantRepository.findByCustomer_CustomerId(customerId);
//    }
//
//    public FavoriteRestaurant addFavoriteRestaurant(FavoriteRestaurant favoriteRestaurant) {
//        return favoriteRestaurantRepository.save(favoriteRestaurant);
//    }
//
//    public void removeFavoriteRestaurant(int customerId, int restaurantId) {
//        favoriteRestaurantRepository.deleteByCustomer_CustomerIdAndRestaurant_RestaurantId(customerId, restaurantId);
//    }
//}
//



