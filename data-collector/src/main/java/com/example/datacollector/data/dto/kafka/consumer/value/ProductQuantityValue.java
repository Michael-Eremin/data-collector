package com.example.datacollector.data.dto.kafka.consumer.value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductQuantityValue {
    @JsonProperty("orderId")
    private Long orderId;
    @JsonProperty("orderLineId")
    private Integer orderLineId;
    @JsonProperty("productWeight")
    private Float productWeight;

}
