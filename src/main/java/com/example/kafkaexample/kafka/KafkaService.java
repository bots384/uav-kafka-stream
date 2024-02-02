package com.example.kafkaexample.kafka;

import com.example.kafkaexample.memory.HLSQueue;
import com.example.kafkaexample.memory.Packet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Service
@Configuration
public class KafkaService {


    @Value("${kafka_topic}")
    private String kafka_topic;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    public void startSendingVideo2Kafka() throws Exception{
        while(true){
            while(!HLSQueue.hlsQueue.isEmpty()){
                Packet packet = HLSQueue.hlsQueue.poll();
                kafkaTemplate.send(kafka_topic,packet.getKey(), packet.getData());
                System.out.println(kafka_topic);
                System.out.println(packet.getKey());
                System.out.println("Sent HLS content to Kafka!!!!!!!!!!!!!!!!!");
            }
        }
    }
}
