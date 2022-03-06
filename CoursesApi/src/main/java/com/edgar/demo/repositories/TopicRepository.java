package com.edgar.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.edgar.demo.models.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
	Topic findByName(String name);
}
