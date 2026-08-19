/**
 * ============================================================================
 * CLASSE: FinancialTransaction
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Financial
 *
 * RESPONSABILIDADE:
 *
 * Representar uma movimentação financeira do Genesis.
 *
 * Uma movimentação pode representar:
 *
 * • Uma entrada de dinheiro.
 * • Uma saída de dinheiro.
 *
 * A entidade mantém as regras básicas do domínio.
 *
 * ============================================================================
 */
package com.genesis.domain.financial;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Entidade base do domínio.
 *
 * Fornece:
 * • id
 * • createdAt
 * • updatedAt
 * • touch()
 */
import com.genesis.domain.shared.entity.BaseEntity;

/*
 * Value Object utilizado para representar valores monetários.
 */
import com.genesis.domain.shared.valueobject.Money;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade que representa uma movimentação financeira.
 */
public class FinancialTransaction extends BaseEntity {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Descrição da movimentação.
     *
     * Exemplo:
     *
     * "Compra de material de limpeza"
     */
    private String description;

    /*
     * Valor financeiro da movimentação.
     */
    private Money amount;

    /*
     * Tipo da movimentação:
     *
     * INCOME
     * EXPENSE
     */
    private FinancialTransactionType type;

    /*
     * Categoria financeira da movimentação.
     */
    private FinancialCategory category;

    /*
     * Forma utilizada para realizar o pagamento
     * ou recebimento.
     */
    private PaymentMethod paymentMethod;

    /*
     * Data em que a movimentação ocorreu.
     */
    private LocalDateTime transactionDate;

    /*
     * Observações adicionais.
     */
    private String notes;

    /*
     * Indica se a movimentação está ativa.
     *
     * TRUE  = movimentação ativa
     * FALSE = excluída logicamente
     */
    private boolean active = true;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Cria uma nova movimentação financeira.
     */
    public FinancialTransaction(
        String description,
        Money amount,
        FinancialTransactionType type,
        FinancialCategory category,
        PaymentMethod paymentMethod,
        LocalDateTime transactionDate,
        String notes) {

        /*
         * Valida a descrição.
         */
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(
                "Transaction description is required."
            );
        }

        /*
         * Valida o valor.
         */
        if (amount == null) {
            throw new IllegalArgumentException(
                "Transaction amount is required."
            );
        }

        /*
         * Valida o tipo.
         */
        if (type == null) {
            throw new IllegalArgumentException(
                "Transaction type is required."
            );
        }

        /*
         * Valida a categoria.
         */
        if (category == null) {
            throw new IllegalArgumentException(
                "Transaction category is required."
            );
        }

        /*
         * Valida a forma de pagamento.
         */
        if (paymentMethod == null) {
            throw new IllegalArgumentException(
                "Payment method is required."
            );
        }

        /*
         * Valida a data da movimentação.
         */
        if (transactionDate == null) {
            throw new IllegalArgumentException(
                "Transaction date is required."
            );
        }

        /*
         * Normaliza a descrição removendo espaços
         * no início e no final.
         */
        this.description = description.trim();

        this.amount = amount;
        this.type = type;
        this.category = category;
        this.paymentMethod = paymentMethod;
        this.transactionDate = transactionDate;
        this.notes = notes;
    }

    /*
     * ============================================================================
     * CONSTRUTOR DE PERSISTÊNCIA
     * ============================================================================
     *
     * Utilizado pelo Mapper quando precisamos
     * reconstruir uma entidade existente a partir
     * dos dados armazenados no banco.
     */
    public FinancialTransaction(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String description,
        Money amount,
        FinancialTransactionType type,
        FinancialCategory category,
        PaymentMethod paymentMethod,
        LocalDateTime transactionDate,
        String notes,
        boolean active) {

        super(id, createdAt, updatedAt);

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(
                "Transaction description is required."
            );
        }

        if (amount == null) {
            throw new IllegalArgumentException(
                "Transaction amount is required."
            );
        }

        if (type == null) {
            throw new IllegalArgumentException(
                "Transaction type is required."
            );
        }

        if (category == null) {
            throw new IllegalArgumentException(
                "Transaction category is required."
            );
        }

        if (paymentMethod == null) {
            throw new IllegalArgumentException(
                "Payment method is required."
            );
        }

        if (transactionDate == null) {
            throw new IllegalArgumentException(
                "Transaction date is required."
            );
        }

        this.description = description.trim();
        this.amount = amount;
        this.type = type;
        this.category = category;
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

    public Money getAmount() {
        return amount;
    }

    public FinancialTransactionType getType() {
        return type;
    }

    public FinancialCategory getCategory() {
        return category;
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

    public boolean isActive() {
        return active;
    }

    /*
     * ============================================================================
     * MÉTODO: changeDescription()
     * ============================================================================
     *
     * Altera a descrição da movimentação.
     */
    public void changeDescription(String newDescription) {

        if (newDescription == null || newDescription.isBlank()) {
            throw new IllegalArgumentException(
                "Transaction description is required."
            );
        }

        this.description = newDescription.trim();

        /*
         * Atualiza updatedAt.
         */
        touch();
    }

    /*
     * ============================================================================
     * MÉTODO: changeAmount()
     * ============================================================================
     *
     * Altera o valor da movimentação.
     */
    public void changeAmount(Money newAmount) {

        if (newAmount == null) {
            throw new IllegalArgumentException(
                "Transaction amount is required."
            );
        }

        this.amount = newAmount;

        /*
         * Atualiza updatedAt.
         */
        touch();
    }

    /*
     * ============================================================================
     * MÉTODO: changeCategory()
     * ============================================================================
     *
     * Altera a categoria da movimentação.
     */
    public void changeCategory(FinancialCategory newCategory) {

        if (newCategory == null) {
            throw new IllegalArgumentException(
                "Transaction category is required."
            );
        }

        this.category = newCategory;

        touch();
    }

    /*
     * ============================================================================
     * MÉTODO: changePaymentMethod()
     * ============================================================================
     *
     * Altera a forma de pagamento.
     */
    public void changePaymentMethod(PaymentMethod newPaymentMethod) {

        if (newPaymentMethod == null) {
            throw new IllegalArgumentException(
                "Payment method is required."
            );
        }

        this.paymentMethod = newPaymentMethod;

        touch();
    }

    /*
     * ============================================================================
     * MÉTODO: changeTransactionDate()
     * ============================================================================
     *
     * Altera a data da movimentação.
     */
    public void changeTransactionDate(
        LocalDateTime newTransactionDate) {

        if (newTransactionDate == null) {
            throw new IllegalArgumentException(
                "Transaction date is required."
            );
        }

        this.transactionDate = newTransactionDate;

        touch();
    }

    /*
     * ============================================================================
     * MÉTODO: changeNotes()
     * ============================================================================
     *
     * Altera as observações da movimentação.
     */
    public void changeNotes(String newNotes) {

        this.notes = newNotes;

        touch();
    }

    /*
     * ============================================================================
     * MÉTODO: changeType()
     * ============================================================================
     *
     * Altera o tipo da movimentação financeira.
     */
    public void changeType(FinancialTransactionType newType) {

        if (newType == null) {
            throw new IllegalArgumentException(
                "Transaction type is required."
            );
        }

        this.type = newType;

        /*
         * Atualiza a data de alteração.
         */
        touch();

    }

    /*
     * ============================================================================
     * MÉTODO: deactivate()
     * ============================================================================
     *
     * Realiza a exclusão lógica da movimentação.
     */
    public void deactivate() {

        this.active = false;

        touch();
    }
}
