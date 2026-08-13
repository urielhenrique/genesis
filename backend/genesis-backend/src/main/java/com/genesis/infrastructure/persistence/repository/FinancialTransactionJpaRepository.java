/**
 * ============================================================================
 * INTERFACE: FinancialTransactionJpaRepository
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Repository
 *
 * RESPONSABILIDADE:
 *
 * Fornecer acesso ao banco de dados para a entidade
 * FinancialTransactionJpaEntity através do Spring Data JPA.
 *
 * ============================================================================
 */
package com.genesis.infrastructure.persistence.repository;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Entidade JPA que representa a tabela
 * financial_transaction.
 */
import com.genesis.infrastructure.persistence.entity.FinancialTransactionJpaEntity;

/*
 * Interface do Spring Data JPA.
 *
 * Fornece automaticamente operações como:
 *
 * • save()
 * • findById()
 * • findAll()
 * • delete()
 */
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Identificador da entidade.
 */
import java.util.UUID;

/**
 * Repository JPA para movimentações financeiras.
 */
public interface FinancialTransactionJpaRepository
    extends JpaRepository<FinancialTransactionJpaEntity, UUID> {

}
