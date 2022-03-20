package com.edgar.transaction.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edgar.transaction.model.Status;

public interface StatusRepo extends CrudRepository<Status, Long> {
	Optional<Status> findByName(String name);
}
