package com.example.hello.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Value("${spring.kafka.consumer.bootstrap-server}")
    private String BOOTSTRAP_SERVER;

    public Map<String, Object> consumerConfigs() {
        Map<String, Object> consumerConfigMap = new HashMap<>();
        consumerConfigMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        consumerConfigMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        consumerConfigMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        consumerConfigMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfigMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfigMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        return consumerConfigMap;
    }


    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConcurrency(1);
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(true);

        return factory;
    }
}
