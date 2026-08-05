package com.genesis.infrastructure.persistence.repository;

import com.genesis.infrastructure.persistence.entity.InventoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryJpaRepository
    extends JpaRepository<InventoryJpaEntity, UUID> {

    Optional<InventoryJpaEntity> findByProductId(UUID productId);

}
