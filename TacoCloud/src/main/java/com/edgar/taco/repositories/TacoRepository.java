package com.edgar.taco.repositories;

import org.springframework.data.repository.CrudRepository;

import com.edgar.taco.models.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
//	Taco save(Taco taco);
}
