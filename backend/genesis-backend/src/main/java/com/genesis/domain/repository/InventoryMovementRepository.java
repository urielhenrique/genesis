package com.genesis.domain.repository;

import com.genesis.domain.inventory.InventoryMovement;

public interface InventoryMovementRepository {

    InventoryMovement save(InventoryMovement movement);

}
