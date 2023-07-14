package com.vasc.vasc_api.repositories;

import com.vasc.vasc_api.entities.Cart;
import com.vasc.vasc_api.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart,Integer> {
    public List<Cart> findAllProductByUserId(Integer id);
}
