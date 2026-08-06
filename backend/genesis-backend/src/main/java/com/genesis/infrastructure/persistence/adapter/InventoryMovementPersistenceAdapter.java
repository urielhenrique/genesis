package com.genesis.infrastructure.persistence.adapter;

import com.genesis.domain.inventory.InventoryMovement;
import com.genesis.domain.repository.InventoryMovementRepository;
import com.genesis.infrastructure.persistence.mapper.InventoryMovementPersistenceMapper;
import com.genesis.infrastructure.persistence.repository.InventoryMovementJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryMovementPersistenceAdapter
    implements InventoryMovementRepository {

    private final InventoryMovementJpaRepository jpaRepository;

    private final InventoryMovementPersistenceMapper mapper;

    public InventoryMovementPersistenceAdapter(
        InventoryMovementJpaRepository jpaRepository,
        InventoryMovementPersistenceMapper mapper) {

        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public InventoryMovement save(InventoryMovement movement) {

        jpaRepository.save(
            mapper.toJpaEntity(movement)
        );

        return movement;
    }

}
