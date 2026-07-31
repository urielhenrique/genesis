package com.genesis.infrastructure.persistence.adapter;

import com.genesis.domain.product.Product;
import com.genesis.domain.repository.ProductRepository;
import com.genesis.infrastructure.persistence.entity.ProductJpaEntity;
import com.genesis.infrastructure.persistence.mapper.ProductPersistenceMapper;
import com.genesis.infrastructure.persistence.repository.ProductJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductPersistenceAdapter implements ProductRepository {

    private final ProductJpaRepository jpaRepository;
    private final ProductPersistenceMapper mapper;

    public ProductPersistenceAdapter(
        ProductJpaRepository jpaRepository,
        ProductPersistenceMapper mapper) {

        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {

        ProductJpaEntity entity = mapper.toJpaEntity(product);

        ProductJpaEntity savedEntity = jpaRepository.save(entity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(UUID id) {

        return jpaRepository
            .findById(id)
            .map(mapper::toDomain);
    }

    @Override
    public Optional<Product> findByName(String name) {

        return jpaRepository
            .findByName(name)
            .map(mapper::toDomain);
    }

    @Override
    public List<Product> findAll() {

        return jpaRepository
            .findAll()
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public void delete(Product product) {

        jpaRepository.delete(
            mapper.toJpaEntity(product)
        );
    }
}
