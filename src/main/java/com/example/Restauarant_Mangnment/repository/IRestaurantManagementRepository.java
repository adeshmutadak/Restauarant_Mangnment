package com.example.Restauarant_Mangnment.repository;

import com.example.Restauarant_Mangnment.model.Restaurant;
import com.example.Restauarant_Mangnment.model.RestaurantManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantManagementRepository extends JpaRepository<RestaurantManagement,Long> {
    RestaurantManagement findByRestaurant(Restaurant existingRestaurant);

    RestaurantManagement findByRestaurantManagementRecordId(Long restaurantManagementRecordId);
}
