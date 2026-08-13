/**
 * ============================================================================
 * CLASSE: FinancialCategoryJpaEntity
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Entity
 *
 * RESPONSABILIDADE:
 *
 * Representar a tabela financial_category dentro
 * da camada de persistência.
 *
 * Esta classe é específica do JPA/Hibernate.
 *
 * Ela não é a entidade de domínio.
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
 * Anotações utilizadas pelo JPA/Hibernate para
 * mapear a classe para uma tabela do banco.
 */
import jakarta.persistence.*;

/*
 * Data e hora utilizadas pelos campos
 * createdAt e updatedAt.
 */
import java.time.LocalDateTime;

/*
 * Identificador único utilizado pelo banco.
 */
import java.util.UUID;

/**
 * Entidade JPA correspondente à tabela financial_category.
 */
@Entity

/*
 * Define o nome da tabela no banco.
 */
@Table(name = "financial_category")
public class FinancialCategoryJpaEntity {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Identificador da categoria.
     */
    @Id
    private UUID id;

    /*
     * Nome da categoria.
     */
    @Column(nullable = false, length = 120)
    private String name;

    /*
     * Indica se a categoria está ativa.
     */
    @Column(nullable = false)
    private boolean active;

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
     * CONSTRUTOR
     * ============================================================================
     *
     * Construtor protegido utilizado pelo JPA/Hibernate.
     */
    protected FinancialCategoryJpaEntity() {
    }

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Cria uma entidade JPA com todos os dados
     * necessários para persistência.
     */
    public FinancialCategoryJpaEntity(
        UUID id,
        String name,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        this.id = id;
        this.name = name;
        this.active = active;
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

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
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
     * ✔ JPA Entity
     * ✔ @Entity
     * ✔ @Table
     * ✔ @Id
     * ✔ @Column
     * ✔ Hibernate
     * ✔ Persistence Layer
     *
     * ============================================================================
     */
}
