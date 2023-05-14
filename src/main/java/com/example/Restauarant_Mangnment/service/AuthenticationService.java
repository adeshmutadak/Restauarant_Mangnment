package com.example.Restauarant_Mangnment.service;

import com.example.Restauarant_Mangnment.model.AuthenticationToken;
import com.example.Restauarant_Mangnment.repository.IAuthTokenRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    IAuthTokenRepository authTokenRepository;

    public void saveToken(AuthenticationToken token) {
        authTokenRepository.save(token);
    }

    public AuthenticationToken getToken(User user){
        return authTokenRepository.findByUser(user);
    }

    public boolean authenticate(String userEmail, String token) {
        AuthenticationToken authenticationToken = authTokenRepository.findFirstByToken(token);
        Optional<String> expectedMail = Optional.ofNullable(authenticationToken.getUser().getUserEmail());
        if(expectedMail.isPresent()){
            return true;
        }else
            return false;
    }
}
