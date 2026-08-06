package com.genesis.infrastructure.persistence.repository;

import com.genesis.infrastructure.persistence.entity.InventoryMovementJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InventoryMovementJpaRepository
    extends JpaRepository<InventoryMovementJpaEntity, UUID> {

    List<InventoryMovementJpaEntity> findByInventoryId(UUID inventoryId);

}
