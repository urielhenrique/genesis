/**
 * ============================================================================
 * CLASSE: FinancialTransactionResponse
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados de uma movimentação financeira
 * que serão retornados pela API.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * FinancialTransaction
 *          ↓
 * FinancialTransactionResponseMapper
 *          ↓
 * FinancialTransactionResponse
 *          ↓
 * JSON
 *
 * ============================================================================
 */
package com.genesis.application.financial.dto;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Tipo da movimentação:
 * INCOME ou EXPENSE.
 */
import com.genesis.domain.financial.FinancialTransactionType;

/*
 * Forma de pagamento ou recebimento.
 */
import com.genesis.domain.financial.PaymentMethod;

/*
 * Valor monetário.
 */
import java.math.BigDecimal;

/*
 * Data e hora da movimentação.
 */
import java.time.LocalDateTime;

/*
 * Identificador único da movimentação e da categoria.
 */
import java.util.UUID;

/**
 * DTO utilizado como resposta da API.
 */
public class FinancialTransactionResponse {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Identificador da movimentação.
     */
    private final UUID id;

    /*
     * Descrição da movimentação.
     */
    private final String description;

    /*
     * Valor da movimentação.
     */
    private final BigDecimal amount;

    /*
     * Tipo da movimentação.
     */
    private final FinancialTransactionType type;

    /*
     * Identificador da categoria.
     */
    private final UUID categoryId;

    /*
     * Nome da categoria.
     *
     * É útil para o Front-end não precisar
     * fazer outra consulta apenas para exibir o nome.
     */
    private final String categoryName;

    /*
     * Forma de pagamento ou recebimento.
     */
    private final PaymentMethod paymentMethod;

    /*
     * Data em que a movimentação ocorreu.
     */
    private final LocalDateTime transactionDate;

    /*
     * Observações adicionais.
     */
    private final String notes;

    /*
     * Data de criação do registro.
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
     *
     * Cria o DTO com os dados que serão enviados
     * para o cliente.
     */
    public FinancialTransactionResponse(
        UUID id,
        String description,
        BigDecimal amount,
        FinancialTransactionType type,
        UUID categoryId,
        String categoryName,
        PaymentMethod paymentMethod,
        LocalDateTime transactionDate,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.paymentMethod = paymentMethod;
        this.transactionDate = transactionDate;
        this.notes = notes;
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

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public FinancialTransactionType getType() {
        return type;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Conceitos:
     *
     * ✔ DTO
     * ✔ Response
     * ✔ Objeto imutável
     * ✔ BigDecimal
     * ✔ UUID
     * ✔ LocalDateTime
     * ✔ Enum
     *
     * ============================================================================
     */
}
