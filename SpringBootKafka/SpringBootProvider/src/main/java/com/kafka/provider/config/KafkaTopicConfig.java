package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.name}")
    private String topicName;

    @Bean
    public NewTopic generateTopic(){

        Map<String, String> configurations = new HashMap<>();

        // Delete the message when has not been read.
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        // Time to retention, by default is -1 (NEVER)
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
        // Max size of each segment on bytes, by default 1GB - 1073741824
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
        // Max size of each message on bytes, by default 1MB - 1000012
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");
        return TopicBuilder.name(topicName)         //Topic Name
                .partitions(2)
                .replicas(1)              //Copies on Clusters
                .configs(configurations)             //Map of configurations
                .build();
    }
}
