package com.example.kafkaexample.kafka;

import com.example.kafkaexample.memory.HLSQueue;
import com.example.kafkaexample.memory.Packet;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
@Configuration
public class KafkaService {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void startSendingVideo2Kafka() throws Exception{
        while(true){
            while(!HLSQueue.hlsQueue.isEmpty()){
                Packet packet = HLSQueue.hlsQueue.poll();;
                kafkaTemplate.send("kemea_uav_video",packet.getKey(), packet.getData());
                System.out.println("kemea_uav_video");
                System.out.println(packet.getKey());
                System.out.println("Sent HLS content to Kafka!!!!!!!!!!!!!!!!!");
            }
        }
    }
}
