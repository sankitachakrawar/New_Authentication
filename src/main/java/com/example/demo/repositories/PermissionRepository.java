package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.PermissionEntity;



public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
}
