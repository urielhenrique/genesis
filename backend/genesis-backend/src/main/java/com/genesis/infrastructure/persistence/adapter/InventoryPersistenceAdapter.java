package com.genesis.infrastructure.persistence.adapter;

import com.genesis.domain.inventory.Inventory;
import com.genesis.domain.product.Product;
import com.genesis.domain.repository.InventoryRepository;
import com.genesis.domain.repository.ProductRepository;
import com.genesis.infrastructure.persistence.entity.InventoryJpaEntity;
import com.genesis.infrastructure.persistence.mapper.InventoryPersistenceMapper;
import com.genesis.infrastructure.persistence.repository.InventoryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class InventoryPersistenceAdapter implements InventoryRepository {

    private final InventoryJpaRepository jpaRepository;

    private final InventoryPersistenceMapper mapper;

    private final ProductRepository productRepository;

    public InventoryPersistenceAdapter(
        InventoryJpaRepository jpaRepository,
        InventoryPersistenceMapper mapper,
        ProductRepository productRepository) {

        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
        this.productRepository = productRepository;
    }

    @Override
    public Inventory save(Inventory inventory) {

        InventoryJpaEntity entity = mapper.toJpaEntity(inventory);

        jpaRepository.save(entity);

        return inventory;
    }

    @Override
    public Optional<Inventory> findByProductId(UUID productId) {

        return jpaRepository
            .findByProductId(productId)
            .flatMap(entity ->
                productRepository.findById(entity.getProductId())
                    .map(product -> mapper.toDomain(entity, product))
            );
    }

}
