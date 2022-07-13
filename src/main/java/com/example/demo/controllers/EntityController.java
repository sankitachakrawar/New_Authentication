package com.example.demo.controllers;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EntityRequestDto;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.EntityEntity;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.services.EntityServiceInterface;


@RestController
@RequestMapping("/entity")
public class EntityController {

	public EntityController() {

		// TODO Auto-generated constructor stub
	}

	@Autowired
	private EntityServiceInterface entityServiceInterface;

	@PreAuthorize("hasRole('getAllEntity')")
	@GetMapping()
	public ResponseEntity<?> getAllEntity() {

		List<EntityEntity> entityList = entityServiceInterface.getAllEntity();
		return new ResponseEntity<>(new SuccessResponseDto("Success", "success", entityList), HttpStatus.OK);

	}

	@PreAuthorize("hasRole('addEntity')")
	@PostMapping()
	public ResponseEntity<?> addEntity(@Valid @RequestBody EntityRequestDto entityBody) {

		entityServiceInterface.addEntity(entityBody);
		return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null), HttpStatus.CREATED);

	}

	@PreAuthorize("hasRole('editEntity')")
	@PutMapping("/{id}")
	public ResponseEntity<?> editEntity(@PathVariable(value = "id") Long entityId, @Valid @RequestBody EntityRequestDto entityBody) throws ResourceNotFoundException {

		try {

			entityServiceInterface.editEntity(entityBody, entityId);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "entityNotFound"), HttpStatus.NOT_FOUND);

		}

	}

	@PreAuthorize("hasRole('deleteEntity')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> editEntity(@PathVariable(value = "id") Long entityId) throws ResourceNotFoundException {

		try {

			entityServiceInterface.deleteEntity(entityId);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "entityNotFound"), HttpStatus.NOT_FOUND);

		}

	}

}
