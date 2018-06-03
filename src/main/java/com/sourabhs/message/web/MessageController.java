package com.sourabhs.message.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourabhs.message.model.KafkaCustomMessage;
import com.sourabhs.message.service.MessageReaderService;
@RestController
@RequestMapping(value = "/kafka")
public class MessageController {

	@Autowired
	MessageReaderService msgReader;

	@GetMapping(value = "/getAll")
	public List<KafkaCustomMessage> getAll() {
		return msgReader.getAllMessage();
	}

	@RequestMapping(value = "/getmessage/{val}")
	public KafkaCustomMessage message(@PathVariable("val") int id) {
		return msgReader.getMessage(id);
	}

	@RequestMapping(value = "/getname/{val}")
	public List<KafkaCustomMessage> getName(@PathVariable("val") String message) {
		return msgReader.findName(message);
	}
	
	@RequestMapping(value = "/getmessageVal/{val}")
	public List<KafkaCustomMessage>  getMessageVal(@PathVariable("val") String message) {
		return  msgReader.findMessage(message);
	}
	
	@RequestMapping(value = "/getTimeVal/{val}")
	public List<KafkaCustomMessage>  getTimeVal(@PathVariable("val") Long time) {
		return  msgReader.findTime(time);
	}
}
