package com.example.datacollector.repo.crud;

import com.example.datacollector.data.entity.WarehouseEntity;
import com.example.datacollector.data.entity.id.WarehousePk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends CrudRepository<WarehouseEntity, WarehousePk> {
}
