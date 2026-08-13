/**
 * ============================================================================
 * CLASSE: FinancialTransactionPersistenceMapper
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Mapper
 *
 * RESPONSABILIDADE:
 *
 * Converter entre a entidade de domínio
 * FinancialTransaction e a entidade JPA
 * FinancialTransactionJpaEntity.
 *
 * ============================================================================
 */
package com.genesis.infrastructure.persistence.mapper;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Entidade de domínio da movimentação financeira.
 */
import com.genesis.domain.financial.FinancialTransaction;

/*
 * Value Object utilizado para representar dinheiro.
 */
import com.genesis.domain.shared.valueobject.Money;

/*
 * Entidade utilizada pelo JPA/Hibernate.
 */
import com.genesis.infrastructure.persistence.entity.FinancialTransactionJpaEntity;

/*
 * Permite que o Spring gerencie o Mapper.
 */
import org.springframework.stereotype.Component;

/**
 * Mapper responsável pela conversão de movimentações financeiras.
 */
@Component
public class FinancialTransactionPersistenceMapper {

    /*
     * ============================================================================
     * MÉTODO: toJpaEntity()
     * ============================================================================
     *
     * Converte a entidade de domínio para a entidade JPA.
     *
     * O Money é convertido para BigDecimal porque
     * o banco armazena apenas o valor numérico.
     */
    public FinancialTransactionJpaEntity toJpaEntity(
        FinancialTransaction transaction) {

        return new FinancialTransactionJpaEntity(
            transaction.getId(),
            transaction.getDescription(),
            transaction.getAmount().getValue(),
            transaction.getType(),
            transaction.getCategory().getId(),
            transaction.getPaymentMethod(),
            transaction.getTransactionDate(),
            transaction.getNotes(),
            transaction.getCreatedAt(),
            transaction.getUpdatedAt()
        );
    }

    /*
     * ============================================================================
     * MÉTODO: toDomain()
     * ============================================================================
     *
     * Converte a entidade JPA para a entidade de domínio.
     *
     * O BigDecimal armazenado no banco é convertido
     * novamente para o Value Object Money.
     *
     * A categoria é recebida separadamente porque
     * nossa entidade JPA armazena apenas o categoryId.
     */
    public FinancialTransaction toDomain(
        FinancialTransactionJpaEntity entity,
        com.genesis.domain.financial.FinancialCategory category) {

        return new FinancialTransaction(
            entity.getId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getDescription(),
            new Money(entity.getAmount()),
            entity.getType(),
            category,
            entity.getPaymentMethod(),
            entity.getTransactionDate(),
            entity.getNotes()
        );
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Domain → JPA:
     *
     * Money → BigDecimal
     * Category → categoryId
     *
     * JPA → Domain:
     *
     * BigDecimal → Money
     * categoryId → FinancialCategory
     *
     * ============================================================================
     */
}
