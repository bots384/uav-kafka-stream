package com.example.kafkaexample.api;


import com.example.kafkaexample.hls.HLSService;
import com.example.kafkaexample.kafka.KafkaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/kafka")
public class MessageController {
    private final KafkaService kafkaService;


    @Autowired
    public MessageController(KafkaService kafkaService){
        this.kafkaService = kafkaService;

    }


    @PostMapping("kemea_uav_video")
    public String startStreamingUAVvideo() throws Exception{
        System.out.println("Sending ............");
        //byte[] test = hlsService.getHLSContent();
        kafkaService.startSendingVideo2Kafka();
        return "Sending to Kafka Started !!!!!";
    }


    @PostMapping("kemea_uav_data")
    public void sendUAVdata(@RequestBody MessageRequest request) throws Exception{
        System.out.println("Sending ............" + request.message());
    }
}
