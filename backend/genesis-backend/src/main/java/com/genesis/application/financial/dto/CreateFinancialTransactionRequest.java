/**
 * ============================================================================
 * CLASSE: CreateFinancialTransactionRequest
 * ============================================================================
 *
 * CAMADA:
 * Application -> Financial -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados enviados pelo cliente para
 * criação de uma movimentação financeira.
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
 * DTO utilizado para criação de uma movimentação financeira.
 */
public class CreateFinancialTransactionRequest {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Descrição da movimentação.
     */
    @NotBlank(message = "Transaction description is required.")
    @Size(max = 255)
    private String description;

    /*
     * Valor da movimentação.
     */
    @NotNull(message = "Transaction amount is required.")
    @Positive(message = "Transaction amount must be greater than zero.")
    private BigDecimal amount;

    /*
     * Tipo da movimentação.
     */
    @NotNull(message = "Transaction type is required.")
    private FinancialTransactionType type;

    /*
     * Categoria associada à movimentação.
     */
    @NotNull(message = "Category is required.")
    private UUID categoryId;

    /*
     * Forma de pagamento ou recebimento.
     */
    @NotNull(message = "Payment method is required.")
    private PaymentMethod paymentMethod;

    /*
     * Data e hora em que a movimentação realmente ocorreu.
     */
    @NotNull(message = "Transaction date is required.")
    private LocalDateTime transactionDate;

    /*
     * Observações adicionais.
     */
    @Size(max = 500)
    private String notes;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Construtor utilizado pelo Jackson para transformar
     * o JSON recebido em um objeto Java.
     */
    protected CreateFinancialTransactionRequest() {
    }

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Permite criar o DTO informando todos os dados.
     */
    public CreateFinancialTransactionRequest(
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
     * ✔ @NotNull
     * ✔ @NotBlank
     * ✔ @Positive
     * ✔ @Size
     * ✔ BigDecimal
     * ✔ LocalDateTime
     * ✔ UUID
     * ✔ Enum
     *
     * ============================================================================
     */
}
