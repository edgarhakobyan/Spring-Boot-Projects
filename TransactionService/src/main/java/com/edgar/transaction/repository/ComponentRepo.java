package com.edgar.transaction.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edgar.transaction.model.Component;

public interface ComponentRepo extends CrudRepository<Component, Long> {
	Optional<Component> findByName(String name);
}
