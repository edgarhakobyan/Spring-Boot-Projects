package com.edgar.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.demo.models.Topic;
import com.edgar.demo.repositories.TopicRepository;

@Service
public class TopicServiceImpl implements TopicService {
	@Autowired
	private TopicRepository topicRepository;

	@Override
	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll().forEach((t) -> topics.add(t));
		return topics;
	}

	@Override
	public Optional<Topic> getTopicById(int id) {
//		return topicRepository.findByName(name);
		return topicRepository.findById(id);
	}

	@Override
	public void addTopic(Topic topic) {
		topicRepository.save(topic);
	}

	@Override
	public void updateTopic(int id, Topic topic) {
		topic.setId(id);
		topicRepository.save(topic);
	}

	@Override
	public void deleteTopic(int id) {
		topicRepository.deleteById(id);
	}

}
