package com.ss.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 
 * @author Sourabh
 * 
 * Spring-boot makes it easy to create stand-alone production grade spring application
 * Spring is a application framework 
 * Problems: Huge framework, Lots of Configuration
 *
 */
@SpringBootApplication
public class MessageKafkaApplication {

	public static void main(String[] args) {
		//ConfigurableApplicationContext context = 
				SpringApplication.run(MessageKafkaApplication.class, args);
		//context.close();
	}

	/*@Bean
	public KafkaMessageProducer messageProducer() {
		return new KafkaMessageProducer();
	}

	@Bean
	public KafkaMessageListener messageListener() {
		return new KafkaMessageListener();
	}*/
}


