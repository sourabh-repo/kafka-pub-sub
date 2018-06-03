package com.sourabhs.message.kafkautill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.sourabhs.message.model.KafkaCustomMessage;
import com.sourabhs.message.service.MessageReaderService;

@Component
public class KafkaMessageListener {
	
	@Autowired
	MessageReaderService msgReader;

	/*
	private CountDownLatch latch = new CountDownLatch(3);
	private CountDownLatch partitionLatch = new CountDownLatch(2);
	private CountDownLatch filterLatch = new CountDownLatch(2);
	private CountDownLatch greetingLatch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	public CountDownLatch getPartitionLatch() {
		return partitionLatch;
	}

	public CountDownLatch getFilterLatch() {
		return filterLatch;
	}

	public CountDownLatch getGreetingLatch() {
		return greetingLatch;
	}*/

	// Consumer of Kafka message 
	@KafkaListener(topics = "${kafka.topic.name.message}", group = "foo", containerFactory = "fooKafkaListenerContainerFactory")
	public void listenGroupFoo(String message) {
		System.out.println("Received Messasge in group 'foo': " + message);
		//latch.countDown();
	}

	// Consumer of Kafka message same topic different implenetation
	@KafkaListener(topics = "${kafka.topic.name.message}", group = "bar", containerFactory = "barKafkaListenerContainerFactory")
	public void listenGroupBar(String message) {
		System.out.println("Received Messasge in group 'bar': " + message);
		//latch.countDown();
	}
	
	/*@KafkaListener(topicPartitions = @TopicPartition(topic = "topicName", partitionOffsets = {
			@PartitionOffset(partition = "0", initialOffset = "0"), @PartitionOffset(partition = "3", initialOffset = "0")}))*/
	@KafkaListener(topics = "${kafka.topic.name.message}", containerFactory = "headersKafkaListenerContainerFactory")
	public void listenWithHeaders(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		System.out.println("Received Messasge: " + message + " from partition: " + partition);
		//latch.countDown();
	}

	@KafkaListener(topicPartitions = @TopicPartition(topic = "${kafka.topic.name.partitioned}", partitions = { "0", "3" }))
	public void listenToParition(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partition) {
		System.out.println("Received Message: " + message + " from partition: " + partition);
		msgReader.addMessage(new KafkaCustomMessage(message, "partition", 0));
		//this.partitionLatch.countDown();
	}

	@KafkaListener(topics = "${kafka.topic.name.filtered}", containerFactory = "filterKafkaListenerContainerFactory")
	public void listenWithFilter(String message) {
		System.out.println("Recieved Message in filtered listener: " + message);
		msgReader.addMessage(new KafkaCustomMessage(message, "filter", 0));
		//this.filterLatch.countDown();
	}

	@KafkaListener(topics = "${kafka.topic.name.greetings}", containerFactory = "greetingKafkaListenerContainerFactory")
	public void greetingListener(KafkaCustomMessage msg) {
		System.out.println("Recieved greeting message: " + msg);
		msgReader.addMessage(msg);
		//this.greetingLatch.countDown();
	}


}
