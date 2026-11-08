package com.example.tp1.repository;

import com.example.tp1.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {

}
