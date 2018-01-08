package com.ss.message.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.message.service.KafkaSenderService;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
	
	@Autowired
	KafkaSenderService kafkaSender;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("message") String message) {
		kafkaSender.send(message);
		return "Message sent to the Kafka Topic Successfully";
	}
	
	@RequestMapping(value = "/message/{val}")
	public String message(@PathVariable("val") String val) {
		kafkaSender.sendMessage(val);
		return "Message sent to the Kafka Topic Successfully";
	}
	
	@RequestMapping(value = "/message/filter/{val}")
	public String filter(@PathVariable("val") String message) {
		kafkaSender.sendFilteredMessage(message);
		return "Success Filtered the message";
	}

	@RequestMapping(value = "/message/greet")
	public String greet(@RequestParam("name") String name, @RequestParam("message") String message, 
							@RequestParam("time") long time) {
		kafkaSender.sendCustomMessage(name, message, time);
		return "Success Greeted with Custom Object Message";
	}

	@RequestMapping(value = "/message/part/{val}")
	public String partition(@PathVariable("val") String message) {
		kafkaSender.sendPartitionMessage(message);
		return "Success partition process in the message";
	}

}
