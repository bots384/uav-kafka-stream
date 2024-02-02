package com.example.kafkaexample.api;


import com.example.kafkaexample.hls.HLSService;
import com.example.kafkaexample.kafka.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/kafka")
public class MessageController {
    private final KafkaService kafkaService;


    @Autowired
    public MessageController(KafkaService kafkaService){
        this.kafkaService = kafkaService;

    }


    @PostMapping("kemea_uav_video")
    public void startStreamingUAVvideo() throws Exception{
        System.out.println("Sending ............");
        //byte[] test = hlsService.getHLSContent();
        kafkaService.startSendingVideo2Kafka();
    }


    @PostMapping("kemea_uav_data")
    public void sendUAVdata(@RequestBody MessageRequest request) throws Exception{
        System.out.println("Sending ............" + request.message());
    }
}
