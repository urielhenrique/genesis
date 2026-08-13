/**
 * ============================================================================
 * INTERFACE: FinancialTransactionRepository
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Repository
 *
 * RESPONSABILIDADE:
 *
 * Definir o contrato de persistência das movimentações
 * financeiras.
 *
 * ============================================================================
 */
package com.genesis.domain.repository;

/*
 * Entidade de domínio da movimentação financeira.
 */
import com.genesis.domain.financial.FinancialTransaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Contrato de persistência das movimentações financeiras.
 */
public interface FinancialTransactionRepository {

    /*
     * Salva uma movimentação.
     */
    FinancialTransaction save(FinancialTransaction transaction);

    /*
     * Busca uma movimentação pelo ID.
     */
    Optional<FinancialTransaction> findById(UUID id);

    /*
     * Retorna todas as movimentações.
     */
    List<FinancialTransaction> findAll();

    /*
     * Remove uma movimentação.
     */
    void delete(FinancialTransaction transaction);
}
