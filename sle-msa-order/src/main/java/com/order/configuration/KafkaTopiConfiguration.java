package com.order.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopiConfiguration {

    @Value("${spring.kafka.topic.order}")
    private String orderProductTopic;

    @Bean
    public NewTopic generateTopic() {
        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); // delete borra el mensaje luego de un tiempo, compact mantiene el mas actual
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // Tiempo de retencion de mensajes - defecto -1
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "102400"); // Tamanio max del segmento - 1G
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1024"); // Tamanio max de cada mensaje - iM

        return TopicBuilder.name(orderProductTopic)
                .partitions(1)
                .replicas(1)
                .configs(configurations)
                .build();
    }
}
