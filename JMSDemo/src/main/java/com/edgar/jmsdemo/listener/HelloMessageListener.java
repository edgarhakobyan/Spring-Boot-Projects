package com.edgar.jmsdemo.listener;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.edgar.jmsdemo.config.JMSConfig;
import com.edgar.jmsdemo.model.HelloWorldMessage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HelloMessageListener {
	private final JmsTemplate jmsTemplate;
	
	@JmsListener(destination = JMSConfig.MY_QUEUE)
	public void listen(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message) {
		System.out.println("Listen 1 - I got a message !!!");
		System.out.println(helloWorldMessage);
	}
	
	@JmsListener(destination = JMSConfig.MY_SEND_RECEIVE_QUEUE)
	public void listenAndSend(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message) throws JmsException, JMSException {
		System.out.println("Listen 2 - I got a message !!!");
		System.out.println(helloWorldMessage);
		
		HelloWorldMessage payloadMessage = HelloWorldMessage
				.builder()
				.id(UUID.randomUUID())
				.message("World")
				.build();
		
		jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMessage);
	}
}
