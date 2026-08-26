package com.genesis.infrastructure.persistence.mapper;

import com.genesis.domain.operation.Operation;
import com.genesis.domain.operation.OperationItem;
import com.genesis.domain.product.Product;
import com.genesis.domain.shared.valueobject.Money;
import com.genesis.domain.shared.valueobject.Quantity;
import com.genesis.infrastructure.persistence.entity.OperationItemJpaEntity;
import com.genesis.infrastructure.persistence.entity.OperationJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class OperationPersistenceMapper {

    public OperationJpaEntity toJpaEntity(Operation operation) {

        return new OperationJpaEntity(
            operation.getId(),
            operation.getType(),
            operation.getStatus(),
            operation.getOperationDate(),
            operation.getDescription(),
            operation.getCreatedAt(),
            operation.getUpdatedAt()
        );
    }

    public Operation toDomain(OperationJpaEntity entity) {

        return new Operation(
            entity.getId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getType(),
            entity.getStatus(),
            entity.getOperationDate(),
            entity.getDescription()
        );
    }

    public OperationItemJpaEntity toJpaEntity(
        OperationItem item,
        java.util.UUID operationId) {

        return new OperationItemJpaEntity(
            item.getId(),
            operationId,
            item.getProduct().getId(),
            item.getQuantity().getValue(),
            item.getUnitPrice().getValue(),
            item.getCreatedAt(),
            item.getUpdatedAt()
        );
    }

    public OperationItem toDomain(
        OperationItemJpaEntity entity,
        Product product) {

        return new OperationItem(
            entity.getId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            product,
            new Quantity(entity.getQuantity()),
            new Money(entity.getUnitPrice())
        );
    }
}
