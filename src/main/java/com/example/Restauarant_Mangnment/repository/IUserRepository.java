package com.example.Restauarant_Mangnment.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    User findByUserEmail(String userEmail);

    User findByUserContactNumber(String userContactNumber);
}