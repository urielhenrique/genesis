package com.genesis.application.inventory.mapper;

import com.genesis.application.inventory.dto.InventoryResponse;
import com.genesis.domain.inventory.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryResponseMapper {

    public InventoryResponse toResponse(
        Inventory inventory) {

        return new InventoryResponse(
            inventory.getProduct().getId(),
            inventory.getProduct().getName(),
            inventory.getProduct().getType(),
            inventory.getQuantity().getValue()
        );
    }
}
