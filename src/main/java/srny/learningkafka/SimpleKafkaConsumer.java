package srny.learningkafka;

import java.util.Properties;
import java.util.Arrays;
import org.apache.kafka.clients.consumer.*;


public class SimpleKafkaConsumer {
   public static void main(String[] args) throws Exception {
      //Kafka consumer configuration settings
      Properties props = new Properties();
      
      props.put("bootstrap.servers", "192.168.159.128:9092");
      props.put("group.id", "test");
      props.put("enable.auto.commit", "true");
      props.put("auto.commit.interval.ms", "1000");
      props.put("session.timeout.ms", "30000");
      props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
      props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
      KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
      
      //Kafka Consumer subscribes list of topics here.
      consumer.subscribe(Arrays.asList("my-topic"));
      
      //print the topic name
      System.out.println("Subscribed to topic " + "my-topic");
      
      while (true) {
         ConsumerRecords<String, String> records = consumer.poll(100);
         for (ConsumerRecord<String, String> record : records)
         
         // print the offset,key and value for the consumer records.
         System.out.printf("offset = %d, key = %s, value = %s\n", 
            record.offset(), record.key(), record.value());
      }
   }
}
