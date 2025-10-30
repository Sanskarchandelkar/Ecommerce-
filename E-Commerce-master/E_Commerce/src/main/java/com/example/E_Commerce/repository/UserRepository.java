package com.example.E_Commerce.repository;
import com.example.E_Commerce.entity.Order;
import com.example.E_Commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}