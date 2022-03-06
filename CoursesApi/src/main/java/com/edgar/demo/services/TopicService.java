package com.edgar.demo.services;

import java.util.List;
import java.util.Optional;

import com.edgar.demo.models.Topic;

public interface TopicService {
	List<Topic> getAllTopics();
	Optional<Topic> getTopicById(int id);
	void addTopic(Topic topic);
	void updateTopic(int id, Topic topic);
	void deleteTopic(int id);
}
