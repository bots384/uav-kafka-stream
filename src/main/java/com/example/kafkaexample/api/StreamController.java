package com.example.kafkaexample.api;


import com.example.kafkaexample.kafka.KafkaService;
import com.example.kafkaexample.memory.HLSQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/kafka")
public class StreamController {
    private final KafkaService kafkaService;


    @Autowired
    public StreamController(KafkaService kafkaService){
        this.kafkaService = kafkaService;

    }


    @PostMapping("start_kafka_stream")
    public String startKafkaStreaming() throws Exception{
        System.out.println("Sending ............");
        if(!HLSQueue.doSend2Kafka){
            HLSQueue.doSend2Kafka=true;
            kafkaService.startSendingVideo2Kafka();
            return "Sending to Kafka Started !!!!!";
        }else{
            return "Streaming is enabled!!!!";
        }

    }

    @PostMapping("stop_kafka_stream")
    public String stopKafkaStreaming() throws Exception{
        if(HLSQueue.doSend2Kafka){
            HLSQueue.doSend2Kafka=false;
            System.out.println("Sending to Kafka Stopped !!!!!");
            return "Sending to Kafka Stopped !!!!!";
        }else{
            return "Streaming is disabled!!!!";
        }
    }


    @PostMapping("kemea_uav_data")
    public void sendUAVdata(@RequestBody MessageRequest request) throws Exception{
        System.out.println("Sending ............" + request.message());
    }
}
