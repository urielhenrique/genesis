/**
 * ============================================================================
 * CLASSE: InventoryJpaEntity
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Entity
 *
 * RESPONSABILIDADE:
 *
 * Representar a tabela "inventory" do banco de dados.
 *
 * Esta classe é utilizada exclusivamente pela camada
 * de persistência e não possui regras de negócio.
 *
 * A conversão entre Inventory (Domínio) e
 * InventoryJpaEntity (Persistência) é realizada pelo
 * InventoryPersistenceMapper.
 *
 * ============================================================================
 *
 * DIFERENÇA ENTRE Inventory E InventoryJpaEntity
 *
 * Inventory
 * • Possui regras de negócio.
 * • Controla entrada e saída de estoque.
 * • Pertence ao Domínio.
 *
 * InventoryJpaEntity
 * • Apenas representa a estrutura da tabela.
 * • Não possui regras de negócio.
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
 * Anotações do Jakarta Persistence (JPA).
 *
 * Permitem mapear esta classe para uma tabela
 * do banco de dados.
 */
import jakarta.persistence.*;

/*
 * Representa números decimais com precisão.
 *
 * Utilizado para armazenar a quantidade
 * do estoque.
 */
import java.math.BigDecimal;

/*
 * Representa data e hora.
 *
 * Utilizado para auditoria.
 */
import java.time.LocalDateTime;

/*
 * Identificador único da entidade.
 */
import java.util.UUID;

/**
 * Indica que esta classe representa
 * uma entidade JPA.
 */
@Entity

/**
 * Define o nome da tabela no banco.
 */
@Table(name = "inventory")
public class InventoryJpaEntity {

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
     * Identificador do produto ao qual
     * este estoque pertence.
     *
     * Neste projeto utilizamos apenas o UUID
     * em vez de um relacionamento JPA.
     */
    @Column(name = "product_id", nullable = false)
    private UUID productId;

    /**
     * Quantidade disponível em estoque.
     *
     * precision = quantidade total de dígitos.
     * scale = quantidade de casas decimais.
     */
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal quantity;

    /**
     * Data de criação do registro.
     */
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /**
     * Data da última atualização.
     */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Construtor sem argumentos exigido pelo
     * JPA/Hibernate.
     */
    protected InventoryJpaEntity() {
    }

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Utilizado para criar uma entidade de
     * persistência antes de salvá-la no banco.
     */
    public InventoryJpaEntity(
        UUID id,
        UUID productId,
        BigDecimal quantity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     *
     * Apenas retornam os valores da entidade.
     *
     * Esta classe não possui regras de negócio.
     */

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public BigDecimal getQuantity() {
        return quantity;
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
     * ✔ @Entity
     * ✔ @Table
     * ✔ @Id
     * ✔ @Column
     * ✔ precision
     * ✔ scale
     * ✔ Construtor exigido pelo JPA
     * ✔ Separação entre Domínio e Persistência
     *
     * ============================================================================
     */
}
