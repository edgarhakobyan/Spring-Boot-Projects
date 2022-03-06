package com.edgar.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.edgar.demo.models.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {
	List<Course> findByTopicId(int id);
}
