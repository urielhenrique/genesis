/**
 * ============================================================================
 * CLASSE: ReceiptJpaEntity
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Entity
 *
 * RESPONSABILIDADE:
 *
 * Representar a tabela receipt no banco de dados através
 * do JPA/Hibernate.
 *
 * ============================================================================
 */
package com.genesis.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade JPA correspondente à tabela receipt.
 */
@Entity
@Table(name = "receipt")
public class ReceiptJpaEntity {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Identificador único do comprovante.
     */
    @Id
    private UUID id;

    /*
     * Movimentação financeira associada.
     */
    @Column(name = "financial_transaction_id", nullable = false)
    private UUID financialTransactionId;

    /*
     * Nome original do arquivo.
     */
    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName;

    /*
     * URL ou referência do arquivo.
     */
    @Column(name = "file_url", nullable = false, length = 1000)
    private String fileUrl;

    /*
     * Tipo MIME do arquivo.
     */
    @Column(name = "content_type", nullable = false, length = 100)
    private String contentType;

    /*
     * Data de criação.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /*
     * Data da última atualização.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /*
     * ============================================================================
     * CONSTRUTOR JPA
     * ============================================================================
     */
    protected ReceiptJpaEntity() {
    }

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public ReceiptJpaEntity(
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
