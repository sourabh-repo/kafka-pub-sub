package com.sourabhs.message.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourabhs.message.dao.CustomMessageDao;
import com.sourabhs.message.model.KafkaCustomMessage;

@Service("msgReader")
public class MessageReaderService {
	
	@Autowired
	private CustomMessageDao customMessageDao;
	
	public List<KafkaCustomMessage> getAllMessage() {
		List<KafkaCustomMessage> msg = new ArrayList<>();
		customMessageDao.findAll().forEach(msg::add);
		return msg;
	}
	
	public KafkaCustomMessage getMessage(int id) {
		return customMessageDao.findOne(id);
	}
	
	public void addMessage(KafkaCustomMessage msg) {
		customMessageDao.save(msg);
	}
	
	public void updateMessage(int id, KafkaCustomMessage msg) {
		customMessageDao.save(msg); // Save will update with that particular ID 
	}
	
	public void deleteMessage(KafkaCustomMessage msg) {
		customMessageDao.delete(msg);
	}
	
	public List<KafkaCustomMessage> findMessage(String msg) {
		return customMessageDao.findByMessage(msg);
	}
	
	public List<KafkaCustomMessage> findName(String name) {
		return customMessageDao.findByName(name);
	}
	
	public List<KafkaCustomMessage> findTime(Long time) {
		return customMessageDao.findByTimeInMilliSeconds(time);
	}

}
