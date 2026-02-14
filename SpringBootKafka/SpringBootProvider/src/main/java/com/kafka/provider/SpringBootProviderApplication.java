package com.kafka.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SpringBootProviderApplication {

    @Value("${kafka.topic.name}")
    private String topicName;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProviderApplication.class, args);
	}

    @Bean
    CommandLineRunner init(KafkaTemplate<String, String> kafkaTemplate){
        return args -> {
            kafkaTemplate.send(topicName, "Hello World from Spring Boot Provider!");
        };
    }
}
