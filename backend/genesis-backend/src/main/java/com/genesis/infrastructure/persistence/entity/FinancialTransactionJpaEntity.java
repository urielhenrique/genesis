/**
 * ============================================================================
 * CLASSE: FinancialTransactionJpaEntity
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Entity
 *
 * RESPONSABILIDADE:
 *
 * Representar a tabela financial_transaction no banco
 * de dados através do JPA/Hibernate.
 *
 * Esta classe pertence à infraestrutura e não ao domínio.
 *
 * ============================================================================
 */
package com.genesis.infrastructure.persistence.entity;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Tipos da movimentação financeira.
 */
import com.genesis.domain.financial.FinancialTransactionType;

/*
 * Formas de pagamento ou recebimento.
 */
import com.genesis.domain.financial.PaymentMethod;

/*
 * Anotações utilizadas pelo JPA/Hibernate.
 */
import jakarta.persistence.*;

/*
 * Valor monetário da movimentação.
 */
import java.math.BigDecimal;

/*
 * Data e hora utilizadas no registro.
 */
import java.time.LocalDateTime;

/*
 * Identificador único.
 */
import java.util.UUID;

/**
 * Entidade JPA correspondente à tabela financial_transaction.
 */
@Entity

/*
 * Define o nome da tabela no banco.
 */
@Table(name = "financial_transaction")
public class FinancialTransactionJpaEntity {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Identificador único da movimentação.
     */
    @Id
    private UUID id;

    /*
     * Descrição da movimentação.
     */
    @Column(nullable = false, length = 255)
    private String description;

    /*
     * Valor da movimentação.
     *
     * precision = quantidade total de dígitos.
     *
     * scale = quantidade de casas decimais.
     */
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    /*
     * Tipo da movimentação:
     *
     * INCOME
     * EXPENSE
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private FinancialTransactionType type;

    /*
     * Identificador da categoria financeira.
     *
     * O relacionamento é controlado pelo UUID.
     */
    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    /*
     * Forma de pagamento ou recebimento.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 30)
    private PaymentMethod paymentMethod;

    /*
     * Data em que a movimentação ocorreu.
     */
    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    /*
     * Observações adicionais.
     */
    @Column(length = 500)
    private String notes;

    /*
     * Data de criação do registro.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /*
     * Data da última atualização.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /*
     * Indica se a movimentação está ativa.
     *
     * TRUE  = registro ativo
     * FALSE = excluído logicamente
     */
    @Column(nullable = false)
    private boolean active;

    /*
     * ============================================================================
     * CONSTRUTOR JPA
     * ============================================================================
     *
     * O Hibernate precisa de um construtor sem argumentos
     * para reconstruir a entidade a partir do banco.
     */
    protected FinancialTransactionJpaEntity() {
    }

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Cria uma entidade JPA contendo todos os dados
     * necessários para persistência.
     */
    public FinancialTransactionJpaEntity(
        UUID id,
        String description,
        BigDecimal amount,
        FinancialTransactionType type,
        UUID categoryId,
        PaymentMethod paymentMethod,
        LocalDateTime transactionDate,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean active) {

        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.categoryId = categoryId;
        this.paymentMethod = paymentMethod;
        this.transactionDate = transactionDate;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Conceitos:
     *
     * ✔ JPA Entity
     * ✔ @Entity
     * ✔ @Table
     * ✔ @Id
     * ✔ @Column
     * ✔ @Enumerated
     * ✔ EnumType.STRING
     * ✔ BigDecimal
     * ✔ UUID
     * ✔ LocalDateTime
     * ✔ Persistence Layer
     *
     * ============================================================================
     */
}
