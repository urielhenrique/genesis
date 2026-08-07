/**
 * ============================================================================
 * CLASSE: ProductJpaEntity
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Entity
 *
 * RESPONSABILIDADE:
 *
 * Representar a tabela "product" do banco de dados.
 *
 * Esta NÃO é a entidade de domínio.
 *
 * Ela existe apenas para que o JPA/Hibernate consiga
 * persistir e recuperar os dados do banco.
 *
 * ============================================================================
 *
 * DIFERENÇA ENTRE Product E ProductJpaEntity
 *
 * Product
 * • Possui regras de negócio.
 * • Pertence ao Domínio.
 *
 * ProductJpaEntity
 * • Não possui regras de negócio.
 * • Apenas representa a estrutura da tabela.
 *
 * A conversão entre elas é feita pelo:
 *
 * ProductPersistenceMapper
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
 * Enum utilizado para representar o tipo do produto.
 */
import com.genesis.domain.product.ProductType;

/*
 * Anotações do Jakarta Persistence (JPA).
 *
 * Permitem mapear esta classe para uma tabela
 * do banco de dados.
 */
import jakarta.persistence.*;

/*
 * Representa valores decimais com precisão.
 *
 * Utilizado para armazenar o preço do produto.
 */
import java.math.BigDecimal;

/*
 * Representa data e hora.
 *
 * Utilizado para auditoria.
 */
import java.time.LocalDateTime;

/*
 * Identificador único do produto.
 */
import java.util.UUID;

/**
 * Indica ao JPA que esta classe representa uma entidade.
 */
@Entity

/**
 * Define o nome da tabela no banco de dados.
 */
@Table(name = "product")
public class ProductJpaEntity {

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
     * Nome do produto.
     *
     * nullable = false
     * Campo obrigatório.
     *
     * length = 120
     * Limite máximo de caracteres.
     */
    @Column(nullable = false, length = 120)
    private String name;

    /**
     * Descrição do produto.
     */
    @Column(length = 500)
    private String description;

    /**
     * Valor unitário.
     *
     * precision = quantidade total de dígitos.
     *
     * scale = quantidade de casas decimais.
     */
    @Column(
        name = "unit_price",
        nullable = false,
        precision = 15,
        scale = 2
    )
    private BigDecimal unitPrice;

    /**
     * Tipo do produto.
     *
     * EnumType.STRING salva o nome do Enum
     * em vez da posição numérica.
     *
     * Exemplo:
     *
     * FOOD
     *
     * ao invés de
     *
     * 1
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType type;

    /**
     * Indica se o produto está ativo.
     */
    @Column(nullable = false)
    private boolean active;

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
     * Construtor sem argumentos exigido pelo
     * JPA/Hibernate para reconstrução da entidade
     * a partir do banco de dados.
     */
    protected ProductJpaEntity() {
    }

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Utilizado pela camada de persistência para
     * criar uma entidade antes de salvá-la no banco.
     */
    public ProductJpaEntity(
        UUID id,
        String name,
        String description,
        BigDecimal unitPrice,
        ProductType type,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.type = type;
        this.active = active;
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
     * Esta classe não possui regras de negócio,
     * portanto não há métodos como changePrice()
     * ou rename().
     */

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public ProductType getType() {
        return type;
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
     * Conceitos aprendidos:
     *
     * ✔ Entity JPA
     * ✔ @Entity
     * ✔ @Table
     * ✔ @Id
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
