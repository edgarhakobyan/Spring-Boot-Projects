package com.edgar.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.demo.models.Course;
import com.edgar.demo.models.Topic;
import com.edgar.demo.services.CourseService;
import com.edgar.demo.services.TopicService;

@RestController
public class CourseController {
	@Autowired
	private CourseService service;
	
	@Autowired
	private TopicService topicService;
	
	@RequestMapping("/topics/{topicId}/courses")
	public List<Course> getAllCoursesOfTopic(@PathVariable("topicId") int topicId) {
		return service.getAllCoursesOfTopic(topicId);
	}
	
	@RequestMapping("/topics/{topicId}/courses/{courseId}")
	public Course getCourseById(@PathVariable("courseId") int courseId) {
		return service.getCourseById(courseId);
	}
	
	@PostMapping("/topics/{topicId}/courses")
	public String addNewCourse(@PathVariable("topicId") int topicId, @RequestBody Course course) {
		Optional<Topic> topic = topicService.getTopicById(topicId);
		if (topic.isEmpty()) {
			return "ERROR";
		}
		course.setTopic(topic.get());
		service.addNewCourse(course);
		return "OK";
	}
}
