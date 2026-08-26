package com.genesis.infrastructure.persistence.repository;

import com.genesis.infrastructure.persistence.entity.OperationItemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OperationItemJpaRepository
    extends JpaRepository<OperationItemJpaEntity, UUID> {

    List<OperationItemJpaEntity> findByOperationId(UUID operationId);
}
