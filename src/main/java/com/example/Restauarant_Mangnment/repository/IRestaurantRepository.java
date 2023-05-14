package com.example.Restauarant_Mangnment.repository;

import com.example.Restauarant_Mangnment.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByRestaurantId(Long id);

    void deleteByRestaurantId(Long id);
}