package com.example.datacollector.data.entity;

import com.example.datacollector.data.dto.kafka.consumer.value.inner.WarehouseSpecification;
import com.example.datacollector.data.entity.id.WarehousePk;
import com.example.datacollector.data.entity.inner.WarehouseSpecificationEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "warehouse_topic", schema = "topics")
@IdClass(WarehousePk.class)
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WarehouseEntity {
    //From {Kafka message: }
    @Column(name="ts")
    private Date ts;
    @Column(name="op", columnDefinition = "varchar(4)")
    private String op;

    //From {Kafka message..."pk": }
    @Id
    @Column(name="stock_id", columnDefinition = "varchar(128)")
    private String stockId;
    @Id
    @Column(name="code", columnDefinition = "varchar(128)")
    private String code;

    //From {Kafka message..."data": }
    @Column(name="id")
    private String id;
    @Column(name="product_id")
    private String productId;
    @Column(name="product_name")
    private String productName;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumns({
            @JoinColumn(name="stock_id", referencedColumnName="stock_id", updatable=false),
            @JoinColumn(name="code", referencedColumnName="code", updatable=false)})
    private List<WarehouseSpecificationEntity> specifications;

    //creation ts, update ts
    @Column(name = "created_ts", columnDefinition = "timestamp with time zone")
    private Date createdTs = new Date();
    @Column(name = "updated_ts", columnDefinition = "timestamp with time zone")
    private Date updatedTs;
}
