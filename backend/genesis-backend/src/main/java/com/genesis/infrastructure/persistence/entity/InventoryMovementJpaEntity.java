/**
 * ============================================================================
 * CLASSE: InventoryMovementJpaEntity
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Entity
 *
 * RESPONSABILIDADE:
 *
 * Representar a tabela "inventory_movement" do banco
 * de dados.
 *
 * Esta entidade é utilizada apenas pela camada de
 * persistência para armazenar o histórico das
 * movimentações de estoque.
 *
 * Ela não possui regras de negócio.
 *
 * A conversão entre InventoryMovement (Domínio)
 * e InventoryMovementJpaEntity (Persistência)
 * é realizada pelo InventoryMovementPersistenceMapper.
 *
 * ============================================================================
 *
 * DIFERENÇA ENTRE InventoryMovement E InventoryMovementJpaEntity
 *
 * InventoryMovement
 * • Entity do Domínio.
 * • Representa um evento de negócio.
 * • Possui validações.
 *
 * InventoryMovementJpaEntity
 * • Entity de Persistência.
 * • Apenas representa a estrutura da tabela.
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
 * Enum que representa o tipo da movimentação
 * (Entrada, Saída ou Ajuste).
 */
import com.genesis.domain.inventory.InventoryMovementType;

/*
 * Enum que representa o motivo da movimentação.
 */
import com.genesis.domain.inventory.InventoryMovementReason;

/*
 * Anotações do Jakarta Persistence (JPA).
 */
import jakarta.persistence.*;

/*
 * Representa números decimais com precisão.
 *
 * Utilizado para armazenar a quantidade movimentada.
 */
import java.math.BigDecimal;

/*
 * Representa data e hora.
 *
 * Utilizado para auditoria do registro.
 */
import java.time.LocalDateTime;

/*
 * Identificador único da entidade.
 */
import java.util.UUID;

/**
 * Indica ao JPA que esta classe representa
 * uma entidade persistente.
 */
@Entity

/**
 * Define o nome da tabela no banco de dados.
 */
@Table(name = "inventory_movement")
public class InventoryMovementJpaEntity {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /**
     * Chave primária da tabela.
     */
    @Id
    private UUID id;

    /**
     * Identificador do estoque ao qual
     * esta movimentação pertence.
     */
    @Column(name = "inventory_id", nullable = false)
    private UUID inventoryId;

    /**
     * Tipo da movimentação.
     *
     * O Enum será salvo como texto
     * no banco de dados.
     *
     * Exemplo:
     *
     * ENTRY
     * EXIT
     * ADJUSTMENT
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", nullable = false)
    private InventoryMovementType type;

    /**
     * Motivo da movimentação.
     *
     * Exemplo:
     *
     * PURCHASE
     * LOSS
     * CONSUMPTION
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "movement_reason", nullable = false)
    private InventoryMovementReason reason;

    /**
     * Quantidade movimentada.
     */
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal quantity;

    /**
     * Observações da movimentação.
     *
     * Campo opcional.
     */
    @Column(length = 500)
    private String notes;

    /**
     * Data de criação do registro.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Data da última atualização.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Construtor sem argumentos exigido
     * pelo JPA/Hibernate.
     */
    protected InventoryMovementJpaEntity() {
    }

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Utilizado para criar uma entidade
     * antes de persisti-la no banco.
     */
    public InventoryMovementJpaEntity(
        UUID id,
        UUID inventoryId,
        InventoryMovementType type,
        InventoryMovementReason reason,
        BigDecimal quantity,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        this.id = id;
        this.inventoryId = inventoryId;
        this.type = type;
        this.reason = reason;
        this.quantity = quantity;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     *
     * Apenas retornam os valores armazenados.
     *
     * Esta classe não possui regras de negócio.
     */

    public UUID getId() {
        return id;
    }

    public UUID getInventoryId() {
        return inventoryId;
    }

    public InventoryMovementType getType() {
        return type;
    }

    public InventoryMovementReason getReason() {
        return reason;
    }

    public BigDecimal getQuantity() {
        return quantity;
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
     * Conceitos aprendidos:
     *
     * ✔ Entity JPA
     * ✔ Histórico de movimentações
     * ✔ @Entity
     * ✔ @Table
     * ✔ @Column
     * ✔ @Enumerated
     * ✔ EnumType.STRING
     * ✔ precision
     * ✔ scale
     * ✔ Construtor exigido pelo JPA
     * ✔ Separação entre Domínio e Persistência
     *
     * ============================================================================
     */
}
