package com.example.demo.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.EntityEntity;


public interface EntityRepository extends JpaRepository<EntityEntity, Long> {

	List<EntityEntity> findAllByIsActiveTrue();
	
}
