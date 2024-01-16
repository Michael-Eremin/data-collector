package com.example.datacollector.data.dto.kafka.consumer.value;

import com.example.datacollector.data.dto.kafka.consumer.value.inner.WarehouseSpecification;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WarehouseValue {
    @JsonProperty("id")
    private String id;
    @JsonProperty("productId")
    private String productId;
    @JsonProperty("productName")
    private String productName;

    @JsonProperty("specifications")
    private List<WarehouseSpecification> specifications;

}
