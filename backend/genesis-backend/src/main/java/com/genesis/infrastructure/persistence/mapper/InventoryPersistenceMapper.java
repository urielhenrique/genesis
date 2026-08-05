package com.genesis.infrastructure.persistence.mapper;

import com.genesis.domain.inventory.Inventory;
import com.genesis.domain.product.Product;
import com.genesis.domain.shared.valueobject.Quantity;
import com.genesis.infrastructure.persistence.entity.InventoryJpaEntity;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class InventoryPersistenceMapper {

    public InventoryJpaEntity toJpaEntity(Inventory inventory) {

        return new InventoryJpaEntity(
            inventory.getId(),
            inventory.getProduct().getId(),
            inventory.getQuantity().getValue(),
            inventory.getCreatedAt(),
            inventory.getUpdatedAt()
        );
    }

    public Inventory toDomain(
        InventoryJpaEntity entity,
        Product product) {

        return new Inventory(
            entity.getId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            product,
            new Quantity(entity.getQuantity())
        );
    }


}
