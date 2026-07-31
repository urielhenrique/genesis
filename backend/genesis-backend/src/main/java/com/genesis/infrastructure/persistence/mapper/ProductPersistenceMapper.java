package com.genesis.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import com.genesis.domain.product.Product;
import com.genesis.domain.shared.valueobject.Money;
import com.genesis.infrastructure.persistence.entity.ProductJpaEntity;

@Component
public class ProductPersistenceMapper {

    public ProductJpaEntity toJpaEntity(Product product) {

        return new ProductJpaEntity(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getUnitPrice().getValue(),
            product.getType(),
            product.isActive(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        );
    }

    public Product toDomain(ProductJpaEntity entity) {

        return new Product(
            entity.getId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getName(),
            entity.getDescription(),
            new Money(entity.getUnitPrice()),
            entity.getType(),
            entity.isActive()
        );
    }

}
