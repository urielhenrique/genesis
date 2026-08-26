package com.genesis.infrastructure.persistence.adapter;

import com.genesis.domain.operation.Operation;
import com.genesis.domain.operation.OperationItem;
import com.genesis.domain.repository.OperationRepository;
import com.genesis.domain.repository.ProductRepository;
import com.genesis.domain.product.Product;
import com.genesis.infrastructure.persistence.entity.OperationItemJpaEntity;
import com.genesis.infrastructure.persistence.mapper.OperationPersistenceMapper;
import com.genesis.infrastructure.persistence.repository.OperationItemJpaRepository;
import com.genesis.infrastructure.persistence.repository.OperationJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OperationPersistenceAdapter implements OperationRepository {

    private final OperationJpaRepository operationRepository;
    private final OperationItemJpaRepository operationItemRepository;
    private final OperationPersistenceMapper mapper;
    private final ProductRepository productRepository;

    public OperationPersistenceAdapter(
        OperationJpaRepository operationRepository,
        OperationItemJpaRepository operationItemRepository,
        OperationPersistenceMapper mapper,
        ProductRepository productRepository) {

        this.operationRepository = operationRepository;
        this.operationItemRepository = operationItemRepository;
        this.mapper = mapper;
        this.productRepository = productRepository;
    }

    @Override
    public Operation save(Operation operation) {

        operationRepository.save(
            mapper.toJpaEntity(operation)
        );

        operationItemRepository.deleteAll(
            operationItemRepository.findByOperationId(
                operation.getId()
            )
        );

        for (OperationItem item : operation.getItems()) {

            OperationItemJpaEntity itemEntity =
                mapper.toJpaEntity(
                    item,
                    operation.getId()
                );

            operationItemRepository.save(itemEntity);
        }

        return operation;
    }

    @Override
    public Optional<Operation> findById(UUID id) {

        return operationRepository.findById(id)
            .map(entity -> {

                Operation operation = mapper.toDomain(entity);

                List<OperationItemJpaEntity> itemEntities =
                    operationItemRepository.findByOperationId(id);

                for (OperationItemJpaEntity itemEntity : itemEntities) {

                    Product product = productRepository
                        .findById(itemEntity.getProductId())
                        .orElseThrow(() ->
                            new IllegalStateException(
                                "Product not found: "
                                    + itemEntity.getProductId()
                            )
                        );

                    OperationItem item =
                        mapper.toDomain(itemEntity, product);

                    operation.restoreItem(item);
                }

                return operation;
            });
    }

    @Override
    public List<Operation> findAll() {

        return operationRepository
            .findAll()
            .stream()
            .map(entity -> findById(entity.getId()))
            .flatMap(Optional::stream)
            .toList();
    }

    @Override
    public void delete(Operation operation) {

        operationRepository.deleteById(
            operation.getId()
        );
    }
}
