/**
 * ============================================================================
 * CLASSE: UpdateFinancialTransactionRequest
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados enviados pelo cliente para
 * atualização de uma movimentação financeira.
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
 * Validações da requisição.
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/*
 * Representa valores monetários.
 */
import java.math.BigDecimal;

/*
 * Representa a data e hora da movimentação.
 */
import java.time.LocalDateTime;

/*
 * Identificador da categoria financeira.
 */
import java.util.UUID;

/**
 * DTO utilizado para atualização de uma movimentação financeira.
 */
public class UpdateFinancialTransactionRequest {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Nova descrição da movimentação.
     */
    @NotBlank(message = "Transaction description is required.")
    @Size(max = 255)
    private final String description;

    /*
     * Novo valor da movimentação.
     */
    @NotNull(message = "Transaction amount is required.")
    @Positive(message = "Transaction amount must be greater than zero.")
    private final BigDecimal amount;

    /*
     * Novo tipo da movimentação.
     */
    @NotNull(message = "Transaction type is required.")
    private final FinancialTransactionType type;

    /*
     * Nova categoria da movimentação.
     */
    @NotNull(message = "Category is required.")
    private final UUID categoryId;

    /*
     * Nova forma de pagamento ou recebimento.
     */
    @NotNull(message = "Payment method is required.")
    private final PaymentMethod paymentMethod;

    /*
     * Nova data da movimentação.
     */
    @NotNull(message = "Transaction date is required.")
    private final LocalDateTime transactionDate;

    /*
     * Novas observações.
     */
    @Size(max = 500)
    private final String notes;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public UpdateFinancialTransactionRequest(
        String description,
        BigDecimal amount,
        FinancialTransactionType type,
        UUID categoryId,
        PaymentMethod paymentMethod,
        LocalDateTime transactionDate,
        String notes) {

        this.description = description == null
            ? null
            : description.trim();

        this.amount = amount;
        this.type = type;
        this.categoryId = categoryId;
        this.paymentMethod = paymentMethod;
        this.transactionDate = transactionDate;
        this.notes = notes;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     */

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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getNotes() {
        return notes;
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Conceitos:
     *
     * ✔ DTO
     * ✔ Request
     * ✔ Bean Validation
     * ✔ Objeto Imutável
     * ✔ BigDecimal
     * ✔ LocalDateTime
     * ✔ UUID
     * ✔ Enum
     *
     * ============================================================================
     */
}
