package com.genesis.domain.service;

import com.genesis.domain.inventory.Inventory;
import com.genesis.domain.inventory.InventoryMovement;
import com.genesis.domain.inventory.InventoryMovementReason;
import com.genesis.domain.inventory.InventoryMovementType;
import com.genesis.domain.shared.valueobject.Quantity;

public interface InventoryMovementService {

    InventoryMovement registerMovement(
        Inventory inventory,
        InventoryMovementType type,
        InventoryMovementReason reason,
        Quantity quantity,
        String observation
    );

}
