package com.edgar.transaction.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edgar.transaction.model.Resolution;

public interface ResolutionRepo extends CrudRepository<Resolution, Long> {
	Optional<Resolution> findByName(String name);
}
