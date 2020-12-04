package com.mg.activemq.resource;

import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/publish")
public class PubishController {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Queue queue;
	@Autowired
	private Topic topic;

	@GetMapping("/queue/{msg}")
	public String publishQueue(@PathVariable("msg") final String msg) {
		jmsTemplate.convertAndSend(queue, msg);
		return "success";
	}
	
	@GetMapping("/topic/{msg}")
	public String publishTopic(@PathVariable("msg") final String msg) {
		jmsTemplate.convertAndSend(topic, msg);
		return "success";
	}
}
