package com.edgar.transaction.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edgar.transaction.model.Brand;

public interface BrandRepo extends CrudRepository<Brand, Long> {
	Optional<Brand> findFirstByName(String name);
}
