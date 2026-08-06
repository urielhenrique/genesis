package com.genesis.infrastructure.persistence.mapper;

import com.genesis.domain.inventory.InventoryMovement;
import com.genesis.infrastructure.persistence.entity.InventoryMovementJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class InventoryMovementPersistenceMapper {

    public InventoryMovementJpaEntity toJpaEntity(
        InventoryMovement movement) {

        return new InventoryMovementJpaEntity(
            movement.getId(),
            movement.getInventory().getId(),
            movement.getType(),
            movement.getReason(),
            movement.getQuantity().getValue(),
            movement.getNotes(),
            movement.getCreatedAt(),
            movement.getUpdatedAt()
        );
    }

}
