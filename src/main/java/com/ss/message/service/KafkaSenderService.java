package com.ss.message.service;

/**
 * @author sourabh
 * 
 */
public interface KafkaSenderService {
	public void send(String message); 
	public void sendMessage(String message);
	public void sendPartitionMessage(String message);	
	public void sendFilteredMessage(String message);
	public void sendCustomMessage(String name, String message, long time);

}
