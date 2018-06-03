# Apache Kafka Producer Consumer API
Spring Boot Application for Kafka Producer and Consumer API with Rest URL's

 ##### Producer: an application that send data or message, send data to kafka server
 ##### Message : an small piece of data i.e. array of bytes for kafka
 ##### Consumer: recepient of the data, i.e. read the data from kafka server
 ##### kafka broker: is the kafka server, i.e. it acts as a message broker between consumer and producer
 ##### cluster : group of computers each executing one instance of kafka broker
 ##### topic   : unique name for a data stream i.e. the way producer and consumer will recognize which stream to use
 ##### partition: if topic is huge, kafka divides the topics to store in different cluster and each one stored in system
 			  we decide when the partition and how many partition should happen
 ##### offset  : sequence number to message they arrive in partition, offset are local to partition
 ##### consumer group: group of consumer acting as a single unit, consumers can be created based on partitions for scalability
 				   kafka doesn't allow more than 2 consumers per partition simultaneously to avoid double reading of records
 
 to get a message : topic + partition + offset
 
 ##### Zookeeper: cordination services for distributed systems, came from hadoop
 
 ##### Fault Tolerance: by applying replication factor i.e. creating copies, it taken care by leader and followers
 					leader always maintain copies and other brokers as followers just to copy data from leader
 ##### Replication Factor: It creates a replicate or copy partition based on the topics, you can set this when topic is created	
 
 ****************CMD************************
 ###### start the Zookeeper-
  .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
 
 ###### start the Apache Kafka broker-
 	.\bin\windows\kafka-server-start.bat .\config\server.properties
 
 ###### create a topic with name javainuse-topic, that has only one partition & one replica.
  .\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic javainuse-topic
 
 ###### create a producer to send message to the above created javainuse-topic and send a message - Hello World Javainuse to it-
  .\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic javainuse-topic Hello World Javainuse
 
 ###### start the consumer which listens to the topic javainuse-topic we just created above
  .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic javainuse-topic --from-beginning
 
 ###### describe the topic
  .\bin\windows\kafka-topics.bat --describe --zookeeper localhost:2181 kafkabasic1
  
 ###### Describe topic outputL
 Topic:kafkabasic1       PartitionCount:2        ReplicationFactor:3     Configs:
       Topic: kafkabasic1      Partition: 0    Leader: 2       Replicas: 2,0,1 Isr: 2,0,1
       Topic: kafkabasic1      Partition: 1    Leader: 0       Replicas: 0,1,2 Isr: 0,1,2
 *
  ###### Topic : Name of Topic 
  ###### Partition : Index of Partition, It increases the scalability of application for more consumers to receive the message, 
   			Each message based on the key hash code goes to only one partition 
  ###### Leader : Broker which is leader
  ###### Replicas : 2, 0, 1 means 2 is leader 0 and 1 are copies and are followers, Replica is for fail over mechanism or  fault-tolerance
  ###### Isr : Insync replicas i.e. 2,0,1 all three are in sync 
 
  ****************To Start Multiple Broker in one machine************************
  \config\server.properties create duplicate files
  Change broker.id, listener(port number), log.dirs (path)
  Then start all the apache kafka broker with different properties
  
  
  *********************APACHE KAFKA SERVER PROPERTIES*****************************
  1. broker.id = unique id for the broker
  2. port = listner port number of the broker
  3. log.dirs = log directory 
  4. zookeeper.connect = host name and port number important for forming a cluster, as zookeeper is connection link
  5. delete.topic.enable = by default it is false as it doesn't allow to delete a topic in DEV we can set true
  6. auto.create.topic.enable = default is true topics are created automatically, in prod it should be false to manage topics
  7. default.replication.factor = default copies of topic, which are created automatically
  8. num.partition = default partition for topics which are created automatically
  9. log.retention.ms = default log retention duration i.e. 7 days
  10. log.retention.bytes = default log retention by size i.e. 1GB per partition
