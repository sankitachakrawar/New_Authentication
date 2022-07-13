package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.UserEntity;


public interface AuthRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

}