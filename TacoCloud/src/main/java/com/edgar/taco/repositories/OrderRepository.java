package com.edgar.taco.repositories;

import org.springframework.data.repository.CrudRepository;

import com.edgar.taco.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
//	Order save(Order order);
}
