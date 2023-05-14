package com.example.Restauarant_Mangnment.repository;

import com.example.Restauarant_Mangnment.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodRepository extends JpaRepository<Food, Long> {
}