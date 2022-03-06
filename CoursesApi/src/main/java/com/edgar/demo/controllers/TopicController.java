package com.edgar.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.demo.models.Topic;
import com.edgar.demo.services.TopicService;

@RestController
public class TopicController {
	@Autowired
	private TopicService topicService;

	@RequestMapping("/topics")
	public List<Topic> getTopics() {
		return topicService.getAllTopics();
	}
	
	@RequestMapping("/topics/{id}")
	public Topic getTopicById(@PathVariable("id") int id) {
		return topicService.getTopicById(id).get();
	}
	
	@PostMapping("/topics")
	public String addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
		return "OK";
	}
	
	@PutMapping("/topics/{id}")
	public String updateTopc(@PathVariable("id") int id, @RequestBody Topic topic) {
		topicService.updateTopic(id, topic);
		return "OK";
	}
	
	@DeleteMapping("/topics/{id}")
	public String deleteTopic(@PathVariable("id") int id) {
		topicService.deleteTopic(id);
		return "OK";
	}
}
