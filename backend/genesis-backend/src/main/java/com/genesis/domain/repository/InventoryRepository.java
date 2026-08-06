package com.genesis.domain.repository;

import com.genesis.domain.inventory.Inventory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface InventoryRepository {

    Inventory save(Inventory inventory);

    Optional<Inventory> findByProductId(UUID productId);

    List<Inventory> findAll();

}
