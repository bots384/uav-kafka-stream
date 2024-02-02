package com.example.kafkaexample.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootStrapServers;
    @Value("${spring.kafka.properties.security.protocol}")
    private String securityProtocol;

    @Value("${spring.kafka.properties.sasl.mechanism}")
    private String saslMechanism;

    @Value("${spring.kafka.properties.sasl.jaas.config}")
    private String saslJaasConfig;



    @Bean
    public Map<String, Object> producerConfig(){
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        configProps.put("security.protocol", securityProtocol);
        configProps.put("sasl.mechanism", saslMechanism);
        configProps.put("sasl.jaas.config", saslJaasConfig);
        configProps.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 999999999);
        configProps.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 999999999);
        return configProps;
    }
    @Bean
    public ProducerFactory<String, byte[]> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig()) ;
    }
    @Bean
    public KafkaTemplate <String, byte[]> kafkaTemplate(ProducerFactory <String, byte[]> producerFactory){

        return new KafkaTemplate<>(producerFactory);
    }
}
