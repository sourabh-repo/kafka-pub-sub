package com.sourabhs.message.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sourabhs.message.model.KafkaCustomMessage;

public interface CustomMessageDao  extends CrudRepository<KafkaCustomMessage, Integer>{
	//List<KafkaCustomMessage> getAllMessages();
	//KafkaCustomMessage getMessageById(int messageId);
	//void addMessage(KafkaCustomMessage message);
	//void deleteMessage(int messageId);
	//boolean isMessageExists(String name, String message);
	
	public List<KafkaCustomMessage> findByName(String name);
	public List<KafkaCustomMessage> findByMessage(String name);
	public List<KafkaCustomMessage> findByTimeInMilliSeconds(long value);
}
