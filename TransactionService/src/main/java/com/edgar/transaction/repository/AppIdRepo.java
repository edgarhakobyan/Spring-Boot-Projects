package com.edgar.transaction.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edgar.transaction.model.AppId;

public interface AppIdRepo extends CrudRepository<AppId, Long> {
	Optional<AppId> findByName(String name);
}
