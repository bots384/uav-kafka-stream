package com.example.kafkaexample.hls;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Configuration
public class HLSService {

    @Value("${hls.endpoint.url}")
    private String hlsEndpointUrl;

    @Value("${hls.masterfilename}")
    private String masterfilename;
    private final WebClient webClient;



    public HLSService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.exchangeStrategies(
                ExchangeStrategies.builder()
                        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(999999999)
        ).build()).baseUrl("http://127.0.0.1:8081").build();
    }

    public byte[] fetchHLSFile(String uri) {
        byte[] hlsContent =null;
        try {
            hlsContent = webClient.get()
                    .uri("/hls/"+uri)
                    .accept(MediaType.APPLICATION_OCTET_STREAM)
                    .retrieve()
                    .bodyToMono(byte[].class)
                    .block();

        } catch (Exception e) {
            // Handle exceptions or log errors as needed
            e.printStackTrace();
        }
        return hlsContent;
    }

}
