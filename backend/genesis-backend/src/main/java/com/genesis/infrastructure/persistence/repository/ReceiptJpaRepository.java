/**
 * ============================================================================
 * INTERFACE: ReceiptJpaRepository
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Repository
 *
 * RESPONSABILIDADE:
 *
 * Fornecer acesso à tabela receipt através do Spring Data JPA.
 *
 * ============================================================================
 */
package com.genesis.infrastructure.persistence.repository;

import com.genesis.infrastructure.persistence.entity.ReceiptJpaEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository JPA responsável pela persistência de comprovantes.
 */
public interface ReceiptJpaRepository
    extends JpaRepository<ReceiptJpaEntity, UUID> {

    /*
     * ============================================================================
     * MÉTODO: findByFinancialTransactionId()
     * ============================================================================
     *
     * Busca todos os comprovantes associados a uma
     * movimentação financeira.
     *
     * O Spring Data JPA cria automaticamente a consulta
     * a partir do nome do método.
     */
    List<ReceiptJpaEntity> findByFinancialTransactionId(
        UUID financialTransactionId
    );
}
