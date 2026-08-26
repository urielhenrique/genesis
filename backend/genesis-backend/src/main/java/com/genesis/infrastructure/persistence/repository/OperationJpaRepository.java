package com.genesis.infrastructure.persistence.repository;

import com.genesis.infrastructure.persistence.entity.OperationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OperationJpaRepository
    extends JpaRepository<OperationJpaEntity, UUID> {
}
