package com.example.datacollector.data.entity;

import com.example.datacollector.data.entity.id.ProductQuantityPk;
import com.example.datacollector.data.entity.id.WarehouseSpecificationPk;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "product_quantity_topic", schema = "topics")
@IdClass(ProductQuantityPk.class)
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductQuantityEntity {
    //From {Kafka message: }
    @Column(name="ts")
    private Date ts;
    @Column(name="op", columnDefinition = "varchar(4)")
    private String op;

    //From {Kafka message..."pk": }
    @Id
    @Column(name="quantity_id", columnDefinition = "varchar(128)")
    private String quantityId;
    @Id
    @Column(name="serial_id")
    private Long serialId;

    //From {Kafka message..."data": }
    private Float productWeight;

    //creation ts, update ts
    @Column(name = "created_ts", columnDefinition = "timestamp with time zone")
    private Date createdTs = new Date();
    @Column(name = "updated_ts", columnDefinition = "timestamp with time zone")
    private Date updatedTs;
}
