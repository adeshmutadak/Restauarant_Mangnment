package com.example.RestaurantManagement.controller;


import com.example.Restauarant_Mangnment.dto.SignInInput;
import com.example.Restauarant_Mangnment.dto.SignInOutput;
import com.example.Restauarant_Mangnment.dto.SignUpInput;
import com.example.Restauarant_Mangnment.dto.SignUpOutput;
import com.example.Restauarant_Mangnment.model.Food;
import com.example.Restauarant_Mangnment.service.AuthenticationService;
import com.example.Restauarant_Mangnment.service.FoodService;
import com.example.Restauarant_Mangnment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private FoodService foodService;

    //signup->signUp input -> signUp output

    @PostMapping("/signup")
    public SignUpOutput signUp(@RequestBody SignUpInput signUpInput) {
        return userService.signUp(signUpInput);
    }

    @PostMapping("/signin")
    public SignInOutput signUp(@RequestBody SignInInput signInInput){
        return userService.signIn(signInInput);
    }

    @GetMapping("/food")
    public ResponseEntity<List<Food>> getAllFoods(@RequestParam String userEmail, @RequestParam String token){
        HttpStatus status;
        List<Food> foodList = null;
        //authenticate

        if(authenticationService.authenticate(userEmail,token)){
            foodList = foodService.showFood();
            status = HttpStatus.FOUND;
        }else{
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<List<Food>>(foodList,status);
    }


}

