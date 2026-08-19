package com.genesis.infrastructure.persistence.repository;

import com.genesis.infrastructure.persistence.entity.FinancialTransactionJpaEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FinancialTransactionJpaRepository
    extends JpaRepository<FinancialTransactionJpaEntity, UUID> {

    /*
     * Busca somente movimentações ativas pelo ID.
     *
     * Movimentações com active = false são tratadas
     * como excluídas logicamente.
     */
    Optional<FinancialTransactionJpaEntity> findByIdAndActiveTrue(
        UUID id
    );

    /*
     * Lista somente movimentações ativas.
     */
    List<FinancialTransactionJpaEntity> findAllByActiveTrue();
}
