package com.example.datacollector.repo.crud;

import com.example.datacollector.data.entity.ProductQuantityEntity;
import com.example.datacollector.data.entity.id.ProductQuantityPk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductQuantityRepository extends CrudRepository<ProductQuantityEntity, ProductQuantityPk> {
}
