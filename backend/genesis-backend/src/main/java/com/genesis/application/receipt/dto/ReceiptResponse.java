/**
 * ============================================================================
 * CLASSE: ReceiptResponse
 * ============================================================================
 *
 * CAMADA:
 * Application -> Receipt -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados de um comprovante retornados pela API.
 *
 * ============================================================================
 */
package com.genesis.application.receipt.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO utilizado como resposta da API.
 */
public class ReceiptResponse {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    private final UUID id;

    /*
     * Movimentação financeira relacionada.
     */
    private final UUID financialTransactionId;

    /*
     * Nome original do arquivo.
     */
    private final String fileName;

    /*
     * URL ou referência para o arquivo.
     */
    private final String fileUrl;

    /*
     * Tipo MIME do arquivo.
     */
    private final String contentType;

    /*
     * Data de criação.
     */
    private final LocalDateTime createdAt;

    /*
     * Data da última atualização.
     */
    private final LocalDateTime updatedAt;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */

    public ReceiptResponse(
        UUID id,
        UUID financialTransactionId,
        String fileName,
        String fileUrl,
        String contentType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        this.id = id;
        this.financialTransactionId = financialTransactionId;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.contentType = contentType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     */

    public UUID getId() {
        return id;
    }

    public UUID getFinancialTransactionId() {
        return financialTransactionId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getContentType() {
        return contentType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
