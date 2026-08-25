package com.genesis.infrastructure.persistence.repository;

import com.genesis.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository
    extends JpaRepository<UserJpaEntity, UUID> {

    Optional<UserJpaEntity> findByEmail(String email);

    Optional<UserJpaEntity> findByIdAndActiveTrue(UUID id);

    java.util.List<UserJpaEntity> findAllByActiveTrue();
}
