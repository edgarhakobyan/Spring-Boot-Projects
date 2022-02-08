package com.edgar.recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edgar.recipeapp.models.UnitOfMeasure;

public interface UnitOfMeasureRep extends CrudRepository<UnitOfMeasure, Long> {
	Optional<UnitOfMeasure> findByDescription(String description);
}
