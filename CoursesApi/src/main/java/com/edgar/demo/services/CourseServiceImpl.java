package com.edgar.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.demo.models.Course;
import com.edgar.demo.repositories.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseRepository repository;

	@Override
	public List<Course> getAllCoursesOfTopic(int topicId) {
		return repository.findByTopicId(topicId);
	}

	@Override
	public Course getCourseById(int courseId) {
		return repository.findById(courseId).get();
	}

	@Override
	public void addNewCourse(Course course) {
		repository.save(course);
	}

}
