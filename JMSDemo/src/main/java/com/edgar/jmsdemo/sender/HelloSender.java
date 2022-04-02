package com.edgar.jmsdemo.sender;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.edgar.jmsdemo.config.JMSConfig;
import com.edgar.jmsdemo.model.HelloWorldMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HelloSender {
	private final JmsTemplate jmsTemplate;
	private final ObjectMapper objectMapper;
	
	@Scheduled(fixedRate = 5000)
	public void sendMessage() {
		final UUID uuid = UUID.randomUUID();
		System.out.println("Sending a message " + uuid);
		
		HelloWorldMessage message = HelloWorldMessage
				.builder()
				.id(uuid)
				.message("Hello World!")
				.build();
		
		jmsTemplate.convertAndSend(JMSConfig.MY_QUEUE, message);
		System.out.println("Message sent");
	}
	
	@Scheduled(fixedRate = 5000)
	public void sendReceiveMessage() throws JMSException {
		HelloWorldMessage message = HelloWorldMessage
				.builder()
				.id(UUID.randomUUID())
				.message("Hello")
				.build();
		
		Message receivedMessage = jmsTemplate.sendAndReceive(JMSConfig.MY_SEND_RECEIVE_QUEUE, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				try {
					Message helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
					helloMessage.setStringProperty("_type", "com.edgar.jmsdemo.model.HelloWorldMessage");
					System.out.println("Sending hello !!!");
					return helloMessage;
				} catch (JsonProcessingException e) {
					throw new JMSException("");
				}
			}
		});
		
		System.out.println("Recevied !!!");
		System.out.println(receivedMessage.getBody(String.class));
	}
}
