/**
 * ============================================================================
 * CLASSE: CreateReceiptRequest
 * ============================================================================
 *
 * CAMADA:
 * Application -> Receipt -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados necessários para criação de um comprovante.
 *
 * O arquivo é recebido através de MultipartFile.
 *
 * ============================================================================
 */
package com.genesis.application.receipt.dto;

import jakarta.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * DTO utilizado para criação de comprovantes.
 */
public class CreateReceiptRequest {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Movimentação financeira relacionada ao comprovante.
     */
    @NotNull(message = "Financial transaction is required.")
    private final UUID financialTransactionId;

    /*
     * Arquivo do comprovante.
     */
    @NotNull(message = "Receipt file is required.")
    private final MultipartFile file;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */

    public CreateReceiptRequest(
        UUID financialTransactionId,
        MultipartFile file) {

        this.financialTransactionId = financialTransactionId;
        this.file = file;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     */

    public UUID getFinancialTransactionId() {
        return financialTransactionId;
    }

    public MultipartFile getFile() {
        return file;
    }
}
