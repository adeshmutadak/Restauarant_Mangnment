package com.example.Restauarant_Mangnment.service;

import com.example.Restauarant_Mangnment.model.Food;
import com.example.Restauarant_Mangnment.repository.IFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    IFoodRepository iFoodRepository;

    public List<Food> showFood() {
        return iFoodRepository.findAll();
    }
}
