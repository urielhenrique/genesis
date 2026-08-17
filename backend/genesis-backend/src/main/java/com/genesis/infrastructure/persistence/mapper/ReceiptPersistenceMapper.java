/**
 * ============================================================================
 * CLASSE: ReceiptPersistenceMapper
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Mapper
 *
 * RESPONSABILIDADE:
 *
 * Converter entre:
 *
 * Receipt
 *     ↕
 * ReceiptJpaEntity
 *
 * O Domain não conhece JPA.
 *
 * ============================================================================
 */
package com.genesis.infrastructure.persistence.mapper;

import com.genesis.domain.receipt.Receipt;
import com.genesis.infrastructure.persistence.entity.ReceiptJpaEntity;

import org.springframework.stereotype.Component;

/**
 * Mapper responsável pelas conversões de Receipt.
 */
@Component
public class ReceiptPersistenceMapper {

    /*
     * ============================================================================
     * MÉTODO: toJpaEntity()
     * ============================================================================
     *
     * Converte a entidade de domínio para a entidade JPA.
     */
    public ReceiptJpaEntity toJpaEntity(
        Receipt receipt) {

        return new ReceiptJpaEntity(
            receipt.getId(),
            receipt.getFinancialTransactionId(),
            receipt.getFileName(),
            receipt.getFileUrl(),
            receipt.getContentType(),
            receipt.getCreatedAt(),
            receipt.getUpdatedAt()
        );
    }

    /*
     * ============================================================================
     * MÉTODO: toDomain()
     * ============================================================================
     *
     * Converte a entidade JPA para a entidade de domínio.
     */
    public Receipt toDomain(
        ReceiptJpaEntity entity) {

        return new Receipt(
            entity.getId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getFinancialTransactionId(),
            entity.getFileName(),
            entity.getFileUrl(),
            entity.getContentType()
        );
    }
}
