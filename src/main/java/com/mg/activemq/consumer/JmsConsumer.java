package com.mg.activemq.consumer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Queue queue;

	@JmsListener(destination = "external.queue", containerFactory = "jmsListenerContainerFactory")
	public void consumeQueue(String msg) {
		System.out.println("Queue : " + msg);
	}
	
	@JmsListener(destination = "external.topic", containerFactory = "jmsTopicListenerContainerFactory")
	public void consumeT1(String msg) {
		System.out.println("Topic1 : " + msg);
	}
	
	@JmsListener(destination = "external.topic", containerFactory = "jmsTopicListenerContainerFactory")
	public void consumeT2(String msg) {
		System.out.println("Topic2 : " + msg);
		jmsTemplate.convertAndSend(queue, msg);
	}
}
