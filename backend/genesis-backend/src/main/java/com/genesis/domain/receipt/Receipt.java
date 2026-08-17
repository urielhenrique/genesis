/**
 * ============================================================================
 * CLASSE: Receipt
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Receipt
 *
 * RESPONSABILIDADE:
 *
 * Representar um comprovante associado a uma movimentação financeira.
 *
 * O domínio armazena apenas os dados necessários para identificar
 * e localizar o arquivo.
 *
 * O arquivo físico ficará em um serviço de armazenamento externo.
 *
 * ============================================================================
 */
package com.genesis.domain.receipt;

import com.genesis.domain.shared.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade de domínio responsável por representar um comprovante.
 */
public class Receipt extends BaseEntity {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Identificador da movimentação financeira à qual
     * o comprovante pertence.
     */
    private final UUID financialTransactionId;

    /*
     * Nome original do arquivo.
     */
    private final String fileName;

    /*
     * URL ou referência utilizada para acessar o arquivo.
     */
    private final String fileUrl;

    /*
     * Tipo MIME do arquivo.
     *
     * Exemplos:
     *
     * image/jpeg
     * image/png
     * application/pdf
     */
    private final String contentType;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Cria um novo comprovante.
     */
    public Receipt(
        UUID financialTransactionId,
        String fileName,
        String fileUrl,
        String contentType) {

        if (financialTransactionId == null) {
            throw new IllegalArgumentException(
                "Financial transaction is required."
            );
        }

        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException(
                "File name is required."
            );
        }

        if (fileUrl == null || fileUrl.isBlank()) {
            throw new IllegalArgumentException(
                "File URL is required."
            );
        }

        if (contentType == null || contentType.isBlank()) {
            throw new IllegalArgumentException(
                "Content type is required."
            );
        }

        this.financialTransactionId = financialTransactionId;
        this.fileName = fileName.trim();
        this.fileUrl = fileUrl.trim();
        this.contentType = contentType.trim();
    }

    /*
     * ============================================================================
     * CONSTRUTOR DE PERSISTÊNCIA
     * ============================================================================
     *
     * Utilizado para reconstruir o objeto a partir do banco.
     */
    public Receipt(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UUID financialTransactionId,
        String fileName,
        String fileUrl,
        String contentType) {

        super(id, createdAt, updatedAt);

        this.financialTransactionId = financialTransactionId;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.contentType = contentType;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     */

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
}
