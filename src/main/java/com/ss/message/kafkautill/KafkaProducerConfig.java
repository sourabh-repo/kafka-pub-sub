package com.ss.message.kafkautill;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.scheduling.annotation.EnableAsync;

import com.ss.message.model.KafkaCustomMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Sourabh
 * 
 * Pending:
 * Send Data based on Partiition
 * Change in Custom Object
 */
@EnableAsync
@Configuration
public class KafkaProducerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    /**
     * Producer for String Object message
     * Producer Properties setting
     * Serialize String Object
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress); // List of Kafka brokers
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * KafkaTemplate for String Object message
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * Producer for Custom Object message
     * Serialize Custom Object
     */
    @Bean
    public ProducerFactory<String, KafkaCustomMessage> messageProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        //configProps.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartinoner.class); For Custom Partition 
        //configProps.put(ProducerConfig.ACKS_CONFIG, 0); //Maximun throughput as we don't have to wait for Ack message
        //configProps.put(ProducerConfig.ACKS_CONFIG, 1); //Chance of lossing message less but not may lose message also
        //configProps.put(ProducerConfig.ACKS_CONFIG, "all"); // Zero chance of loosing message but slower
        //configProps.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1); // Bigger number means bigger in flight based on server memory 
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * KafkaTemplate for Custom Object message
     */
    @Bean
    public KafkaTemplate<String, KafkaCustomMessage> messageKafkaTemplate() {
        return new KafkaTemplate<>(messageProducerFactory());
    }
}
