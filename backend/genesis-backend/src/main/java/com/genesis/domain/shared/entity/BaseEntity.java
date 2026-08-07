/**
 * ============================================================================
 * CLASSE: BaseEntity
 * ============================================================================
 *
 * CAMADA:
 * Domain (Domínio)
 *
 * RESPONSABILIDADE:
 * Esta é a classe base de todas as entidades do sistema Genesis.
 *
 * Toda entidade que representa algo importante do negócio
 * deve herdar desta classe.
 *
 * Exemplos:
 *
 * Product
 * Inventory
 * InventoryMovement
 * FinancialTransaction
 *
 * Todas elas possuem características em comum:
 *
 * • id
 * • createdAt
 * • updatedAt
 *
 * Em vez de repetir esse código em todas as entidades,
 * colocamos tudo nesta classe.
 *
 * Isto é chamado de HERANÇA.
 *
 * QUEM UTILIZA:
 *
 * Todas as entidades do domínio.
 *
 * QUEM ESTA CLASSE UTILIZA:
 *
 * UUID
 * LocalDateTime
 * Money
 *
 * OBSERVAÇÃO:
 *
 * Esta classe é ABSTRACT.
 *
 * Isso significa que ela NÃO pode ser criada diretamente.
 *
 * O correto é criar uma classe filha:
 *
 * Product extends BaseEntity
 *
 * Inventory extends BaseEntity
 *
 * ============================================================================
 */

package com.genesis.domain.shared.entity;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Money representa um Value Object de dinheiro.
 *
 * Está declarado aqui porque algumas entidades
 * poderão sobrescrever o método getUnitPrice().
 */
import com.genesis.domain.shared.valueobject.Money;

/*
 * LocalDateTime representa uma data e hora.
 *
 * Utilizamos para registrar quando uma entidade
 * foi criada e quando foi alterada.
 */
import java.time.LocalDateTime;

/*
 * Objects fornece métodos utilitários.
 *
 * Aqui utilizamos Objects.equals()
 * e Objects.hash().
 */
import java.util.Objects;

/*
 * UUID representa um identificador único.
 *
 * É utilizado como chave primária de todas
 * as entidades do Projeto genesis.
 *
 * Exemplo:
 *
 * 550e8400-e29b-41d4-a716-446655440000
 */
import java.util.UUID;

public abstract class BaseEntity {

    /*
     * ========================================================================
     * ATRIBUTOS
     * ========================================================================
     */

    /*
     * Identificador único da entidade.
     *
     * Final significa que nunca poderá ser alterado
     * depois da criação do objeto.
     */
    private final UUID id;

    /*
     * Data de criação da entidade.
     *
     * Também nunca poderá ser alterada.
     */
    private final LocalDateTime createdAt;

    /*
     * Data da última alteração.
     *
     * Sempre que algum dado da entidade muda,
     * este campo é atualizado.
     */
    private LocalDateTime updatedAt;

    /*
     * ========================================================================
     * CONSTRUTOR
     * ========================================================================
     *
     * Este construtor é utilizado quando estamos
     * criando uma entidade.
     *
     * Exemplo:
     *
     * Product novoProduto = new Product(...);
     *
     * Automaticamente são gerados:
     *
     * • UUID
     * • createdAt
     * • updatedAt
     */
    protected BaseEntity() {

        this(
            UUID.randomUUID(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

    /*
     * ========================================================================
     * CONSTRUTOR
     * ========================================================================
     *
     * Este construtor é utilizado quando a entidade
     * está sendo reconstruída a partir do banco.
     *
     * Exemplo:
     *
     * PostgreSQL
     *        ↓
     * JPA Entity
     *        ↓
     * Domain Entity
     *
     * Neste caso NÃO devemos gerar um novo UUID,
     * pois ele já existe.
     */
    protected BaseEntity(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        if (id == null) {
            throw new IllegalArgumentException("Id is required.");
        }

        if (createdAt == null) {
            throw new IllegalArgumentException("CreatedAt is required.");
        }

        if (updatedAt == null) {
            throw new IllegalArgumentException("UpdatedAt is required.");
        }

        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /*
     * ========================================================================
     * GETTERS
     * ========================================================================
     *
     * Apenas devolvem informações.
     *
     * Nunca alteram o estado do objeto.
     */

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /*
     * ========================================================================
     * MÉTODO touch()
     * ========================================================================
     *
     * Responsabilidade:
     *
     * Atualizar a data da última modificação.
     *
     * Sempre que um método alterar algum atributo,
     * ele deve chamar touch().
     *
     * Exemplo:
     *
     * changePrice()
     * changeName()
     * increase()
     * decrease()
     */
    protected void touch() {
        updatedAt = LocalDateTime.now();
    }

    /*
     * ========================================================================
     * equals()
     * ========================================================================
     *
     * Define quando duas entidades são consideradas iguais.
     *
     * No Genesis, duas entidades são iguais
     * quando possuem o mesmo UUID.
     *
     * Mesmo que todos os outros atributos sejam diferentes.
     */
    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof BaseEntity entity)) {
            return false;
        }

        return Objects.equals(id, entity.id);
    }

    /*
     * ========================================================================
     * hashCode()
     * ========================================================================
     *
     * Deve sempre ser sobrescrito quando equals()
     * é sobrescrito.
     *
     * É utilizado por estruturas como:
     *
     * HashMap
     * HashSet
     * LinkedHashMap
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
