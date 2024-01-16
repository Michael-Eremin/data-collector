package com.example.datacollector.data.dto.kafka.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaMessage <K, D> {
    @JsonProperty("ts")
    private Date ts;
    @JsonProperty("op")
    private String op;
    @JsonProperty("pk")
    private K pk;
    @JsonProperty("data")
    private D data;
}
