package com.genesis.domain.repository;

import com.genesis.domain.product.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(UUID id);

    Optional<Product> findByName(String name);

    List<Product> findAll();

    void delete(Product product);

}
