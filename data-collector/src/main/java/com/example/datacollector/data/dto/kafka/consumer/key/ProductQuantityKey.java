package com.example.datacollector.data.dto.kafka.consumer.key;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductQuantityKey {
    @JsonProperty("quantityId")
    private String quantityId;
    @JsonProperty("serialId")
    private Long serialId;
}
