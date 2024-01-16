package com.example.datacollector.data.dto.kafka.consumer.value.inner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WarehouseSpecification {
    @JsonProperty("specId")
    private Integer specId;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("productValue")
    private Integer productValue;
}
