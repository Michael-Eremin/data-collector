package com.example.datacollector.service;

import com.example.datacollector.data.dto.kafka.consumer.KafkaMessage;

public interface KafkaMessageService<K, D> {
    void processKafkaMessage(KafkaMessage<K, D> msg);
}
