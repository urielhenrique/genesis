/**
 * ============================================================================
 * CLASSE: ReceiptResponseMapper
 * ============================================================================
 *
 * CAMADA:
 * Application -> Receipt -> Mapper
 *
 * RESPONSABILIDADE:
 *
 * Converter a entidade de domínio Receipt para o DTO
 * ReceiptResponse utilizado pela API.
 *
 * ============================================================================
 */
package com.genesis.application.receipt.mapper;

import com.genesis.application.receipt.dto.ReceiptResponse;
import com.genesis.domain.receipt.Receipt;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper responsável pelas respostas de Receipt.
 */
@Component
public class ReceiptResponseMapper {

    /*
     * ============================================================================
     * MÉTODO: toResponse()
     * ============================================================================
     *
     * Converte Receipt → ReceiptResponse.
     */
    public ReceiptResponse toResponse(
        Receipt receipt) {

        return new ReceiptResponse(
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
     * MÉTODO: toResponseList()
     * ============================================================================
     *
     * Converte uma lista de Receipt para uma lista
     * de ReceiptResponse.
     */
    public List<ReceiptResponse> toResponseList(
        List<Receipt> receipts) {

        return receipts.stream()
            .map(this::toResponse)
            .toList();
    }
}
