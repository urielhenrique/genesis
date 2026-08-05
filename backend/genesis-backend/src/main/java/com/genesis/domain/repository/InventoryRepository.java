package com.genesis.domain.repository;

import com.genesis.domain.inventory.Inventory;
import com.genesis.domain.product.Product;

import java.util.Optional;

public interface InventoryRepository {

    Inventory save(Inventory inventory);

    Optional<Inventory> findByProduct(Product product);

}
