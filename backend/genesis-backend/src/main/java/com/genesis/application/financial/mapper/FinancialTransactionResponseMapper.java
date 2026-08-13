/**
 * ============================================================================
 * CLASSE: FinancialTransactionResponseMapper
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> Mapper
 *
 * RESPONSABILIDADE:
 *
 * Converter a entidade de domínio FinancialTransaction
 * para o DTO FinancialTransactionResponse.
 *
 * ============================================================================
 */
package com.genesis.application.financial.mapper;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * DTO retornado pela API.
 */
import com.genesis.application.financial.dto.FinancialTransactionResponse;

/*
 * Entidade de domínio da movimentação financeira.
 */
import com.genesis.domain.financial.FinancialTransaction;

/*
 * Componente gerenciado pelo Spring.
 */
import org.springframework.stereotype.Component;

/**
 * Mapper responsável pelas respostas de movimentações financeiras.
 */
@Component
public class FinancialTransactionResponseMapper {

    /*
     * ============================================================================
     * MÉTODO: toResponse()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Converter uma entidade FinancialTransaction
     * em FinancialTransactionResponse.
     */
    public FinancialTransactionResponse toResponse(
        FinancialTransaction transaction) {

        return new FinancialTransactionResponse(
            transaction.getId(),
            transaction.getDescription(),
            transaction.getAmount().getValue(),
            transaction.getType(),
            transaction.getCategory().getId(),
            transaction.getCategory().getName(),
            transaction.getPaymentMethod(),
            transaction.getTransactionDate(),
            transaction.getNotes(),
            transaction.getCreatedAt(),
            transaction.getUpdatedAt()
        );
    }
}
