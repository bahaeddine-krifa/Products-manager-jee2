package com.example.tp1.repository;

import com.example.tp1.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUserName(String userName);
}
