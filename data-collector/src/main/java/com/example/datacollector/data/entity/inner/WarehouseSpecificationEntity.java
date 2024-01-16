package com.example.datacollector.data.entity.inner;

import com.example.datacollector.data.entity.id.WarehouseSpecificationPk;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "warehouse_specification_topic", schema = "topics")
@IdClass(WarehouseSpecificationPk.class)
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WarehouseSpecificationEntity {
    @Id
    @Column(name="stock_id", columnDefinition = "varchar(128)")
    private String stockId;
    @Id
    @Column(name="code", columnDefinition = "varchar(128)")
    private String code;
    @Id
    @Column(name="spec_id")
    private Integer specId;

    @Column(name="product_id")
    private Integer productId;
    @Column(name="product_value")
    private Integer productValue;
}
