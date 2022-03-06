package com.edgar.demo.services;

import java.util.List;

import com.edgar.demo.models.Course;

public interface CourseService {
	List<Course> getAllCoursesOfTopic(int topicId);
	Course getCourseById(int courseId);
	void addNewCourse(Course course);
}
