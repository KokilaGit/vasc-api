package com.vasc.vasc_api.repositories;

import com.vasc.vasc_api.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Integer> {
}
