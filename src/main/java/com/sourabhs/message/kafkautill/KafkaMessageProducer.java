package com.sourabhs.message.kafkautill;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.sourabhs.message.model.KafkaCustomMessage;

/**
 * 
 * @author Sourabh
 *	
 * Producer Send type:
 * 	Fire and Forget
 *  Synchronous
 *  Asynchronous
 */
@Component
public class KafkaMessageProducer {

	// For Messages containing Only Strings
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	// For Messages Containing Custome Objects
	@Autowired
	private KafkaTemplate<String, KafkaCustomMessage> kafkaTemplateWithMessage;

	//Topics from property files
	@Value(value = "${kafka.topic.name.message}")
	private String topicName;

	@Value(value = "${kafka.topic.name.partitioned}")
	private String partionedTopicName;

	@Value(value = "${kafka.topic.name.filtered}")
	private String filteredTopicName;

	@Value(value = "${kafka.topic.name.greetings}")
	private String greetingTopicName;

	/**
	 * Methods to send the message to produce the Kafka message
	 * Send and Forget producer i.e. Fire And Forget possibility of loosing message
	 */
	public void sendMessage(String message) {
		kafkaTemplate.send(topicName, message);
	}

	// Producer Record send message with topicName, Partition Number, key , value
	public void sendMessageToPartion(String message, int partition, String partitionMessage) {
		kafkaTemplate.send(partionedTopicName, partition, partitionMessage, message);
	}

	/**
	 * Asynchronous approach : Middle way between Fire and Forget and Synchronous method
	 * 						it uses the call back method to track success and failure condition
	 * 						speed almost same as Fire and Forget method
	 */
	@Async
	public void sendMessageToFiltered(String message) {
		ListenableFuture<SendResult<String, String>> futureRes  = kafkaTemplate.send(filteredTopicName, message);
		
		futureRes.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(final SendResult<String, String> message) {
				System.out.println("sent message= " + message + " with offset= " + message.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(final Throwable throwable) {
				System.out.println("unable to send message= " + message + throwable.getMessage());
			}
		});
	}

	/**
	 * ListenableFuture for checking if success or not
	 * Synchronous Send : Check for failure it is little slower
	 * 					 as it always wait for success
	 */
	public void sendGreetingMessage(KafkaCustomMessage msg) {
		ListenableFuture<SendResult<String, KafkaCustomMessage>> res = kafkaTemplateWithMessage.send(greetingTopicName, msg);

		try {
			RecordMetadata recordData = res.get().getRecordMetadata();
			System.out.println("partion: " + recordData.partition() + " offset: " + recordData.offset());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} 

	}
}
