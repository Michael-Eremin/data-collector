package com.example.datacollector.data.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Embeddable
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class WarehousePk implements Serializable {
    @Id
    @Column(name="stock_id", columnDefinition = "varchar(128)")
    private String stockId;
    @Id
    @Column(name="code", columnDefinition = "varchar(128)")
    private String code;
}
