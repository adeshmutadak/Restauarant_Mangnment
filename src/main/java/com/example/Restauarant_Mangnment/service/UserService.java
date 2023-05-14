package com.example.Restauarant_Mangnment.service;

import com.example.Restauarant_Mangnment.dto.SignInInput;
import com.example.Restauarant_Mangnment.dto.SignInOutput;
import com.example.Restauarant_Mangnment.dto.SignUpInput;
import com.example.Restauarant_Mangnment.dto.SignUpOutput;
import com.example.Restauarant_Mangnment.model.AuthenticationToken;
import com.example.Restauarant_Mangnment.model.Restaurant;
import com.example.Restauarant_Mangnment.repository.IUserRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    AuthenticationService tokenService;

    @Autowired
    RestaurantService restaurantService;


    public SignUpOutput signUp(SignUpInput signUpInput) {
        //check if user already exists
        User user = iUserRepository.findByUserEmail(signUpInput.getUserEmail());
        User user1 = iUserRepository.findByUserContactNumber(signUpInput.getUserContactNumber());
        if(user != null){
            throw new IllegalStateException("Email already registered..!!!");
        } else if (user1 != null) {
            throw new IllegalStateException("Phone number already registered with some user..!!");
        }

        //encryption
        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signUpInput.getUserPassword());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        //save the user

        //user = new User(signUpInput.getUserFirstName(),signUpInput.getUserLastName(),signUpInput.getUserEmail(),encryptedPassword,signUpInput.getUserContactNumber());

        iUserRepository.save(user);
        //token creation and saving

        AuthenticationToken token = new AuthenticationToken((com.example.Restauarant_Mangnment.model.User) user);
        tokenService.saveToken(token);

        return new SignUpOutput("Hurrah..!! You have registered","User registered successfully");
    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(userPassword.getBytes());
        byte[] digested = messageDigest.digest();

        String hash = DatatypeConverter.printHexBinary(digested);
        return  hash;

    }

    public SignInOutput signIn(SignInInput signInInput) {
        //get email
        User user = iUserRepository.findByUserEmail(signInInput.getUserEmail());

        if(user == null){
            throw new IllegalStateException("No user with this email..!! Register First");
        }

        //encrypt the password
        String encryptedPassword = null;
        try{
            encryptedPassword = encryptPassword(signInInput.getUserPassword());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        boolean isPasswordMatched = encryptedPassword.equals(user.getUsername());
        if(!isPasswordMatched){
            throw new IllegalStateException("Incorrect Password or email");
        }

        AuthenticationToken authToken = tokenService.getToken(user);


        return new SignInOutput("Authentication Successful",authToken.getToken());
    }

    public List<Restaurant> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }
}