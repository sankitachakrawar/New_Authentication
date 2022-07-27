package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.EntityRequestDto;
import com.example.demo.entities.EntityEntity;
import com.example.demo.exceptionHandling.ResourceNotFoundException;

public interface EntityServiceInterface {

	void addEntity(EntityRequestDto entityDetail);

	List<EntityEntity> getAllEntity();

	void editEntity(EntityRequestDto entityDetail, Long entityId) throws ResourceNotFoundException;

	void deleteEntity(Long entityId) throws ResourceNotFoundException;

}
