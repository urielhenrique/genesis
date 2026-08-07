/**
 * ============================================================================
 * CLASSE: Product
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Product
 *
 * RESPONSABILIDADE:
 *
 * Representa um produto do sistema Genesis.
 *
 * Um produto é uma ENTIDADE do domínio.
 *
 * Exemplos:
 *
 * • Café
 * • Açúcar
 * • Água Mineral
 * • Refrigerante
 * • Camiseta
 *
 * ============================================================================
 *
 * O QUE É UMA ENTITY?
 *
 * Uma Entity representa algo que possui identidade.
 *
 * Exemplo:
 *
 * Produto A
 *
 * UUID:
 *
 * a12f...
 *
 * Mesmo que o nome mude,
 * continua sendo o mesmo produto.
 *
 * Diferente de um Value Object,
 * uma Entity possui identidade própria.
 *
 * ============================================================================
 *
 * ESTA CLASSE É RESPONSÁVEL POR:
 *
 * ✔ armazenar os dados do produto
 *
 * ✔ validar regras
 *
 * ✔ alterar seu próprio estado
 *
 * ✔ proteger seus atributos
 *
 * ============================================================================
 */

package com.genesis.domain.product;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * BaseEntity fornece:
 *
 * • UUID
 * • createdAt
 * • updatedAt
 * • touch()
 *
 * Todas as entidades do Genesis herdam dela.
 */
import com.genesis.domain.shared.entity.BaseEntity;

/*
 * Money representa um valor monetário.
 *
 * Em vez de utilizar BigDecimal diretamente,
 * utilizamos um Value Object.
 */
import com.genesis.domain.shared.valueobject.Money;

public class Product extends BaseEntity {

    /*
     * ========================================================================
     * ATRIBUTOS
     * ========================================================================
     */

    /*
     * Nome do produto.
     *
     * Exemplo:
     *
     * Café Tradicional
     */
    private String name;

    /*
     * Descrição detalhada.
     */
    private String description;

    /*
     * Valor unitário.
     *
     * Utilizamos Money para garantir
     * todas as regras financeiras.
     */
    private Money unitPrice;

    /*
     * Tipo do produto.
     *
     * Exemplo:
     *
     * FOOD
     * DRINK
     * SERVICE
     */
    private ProductType type;

    /*
     * Indica se o produto pode ser utilizado.
     */
    private boolean active;

    /*
     * ========================================================================
     * CONSTRUTOR
     * ========================================================================
     *
     * Utilizado para criar um NOVO produto.
     *
     * Neste momento o BaseEntity cria:
     *
     * • UUID
     * • createdAt
     * • updatedAt
     */

    public Product(
        String name,
        String description,
        Money unitPrice,
        ProductType type) {

        /*
         * Validação da regra:
         *
         * Todo produto deve possuir nome.
         */

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is required.");
        }

        /*
         * Todo produto precisa possuir um preço.
         */

        if (unitPrice == null) {
            throw new IllegalArgumentException("Unit price is required.");
        }

        /*
         * Todo produto deve possuir um tipo.
         */

        if (type == null) {
            throw new IllegalArgumentException("Product type is required.");
        }

        this.name = name.trim();
        this.description = description;
        this.unitPrice = unitPrice;
        this.type = type;
        this.active = true;

    }

    public Product(
        java.util.UUID id,
        java.time.LocalDateTime createdAt,
        java.time.LocalDateTime updatedAt,
        String name,
        String description,
        Money unitPrice,
        ProductType type,
        boolean active) {

        super(id, createdAt, updatedAt);

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is required.");
        }

        if (unitPrice == null) {
            throw new IllegalArgumentException("Unit price is required.");
        }

        if (type == null) {
            throw new IllegalArgumentException("Product type is required.");
        }

        this.name = name.trim();
        this.description = description;
        this.unitPrice = unitPrice;
        this.type = type;
        this.active = active;
    }

    /*
     * ========================================================================
     * GETTERS
     * ========================================================================
     *
     * Apenas devolvem informações.
     *
     * Nunca alteram o estado do produto.
     */

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public ProductType getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }

    /*
     * ========================================================================
     * MÉTODO rename()
     * ========================================================================
     *
     * Responsabilidade:
     *
     * Alterar o nome do produto.
     *
     * Fluxo:
     *
     * 1. Valida o novo nome.
     * 2. Atualiza o atributo.
     * 3. Atualiza updatedAt.
     */

    public void rename(String newName) {

        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Product name is required.");
        }

        this.name = newName.trim();
        touch();
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
        touch();
    }

    /*
     * ============================================================================
     * MÉTODO changePrice()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Alterar o preço do produto.
     *
     * Observe que utilizamos Money,
     * nunca BigDecimal diretamente.
     */

    public void changePrice(Money newPrice) {

        if (newPrice == null) {
            throw new IllegalArgumentException("Unit price is required.");
        }

        this.unitPrice = newPrice;
        touch();
    }

    /*
     * Ativa o produto.
     *
     * Produtos ativos podem ser utilizados
     * pelo restante do sistema.
     */

    public void activate() {
        this.active = true;
        touch();
    }

    /*
     * Desativa o produto.
     *
     * O produto continua existindo,
     * porém deixa de ser utilizado.
     *
     * Isto é chamado de Soft Delete
     * ou Inativação Lógica.
     */

    public void deactivate() {
        this.active = false;
        touch();
    }

    public void changeType(ProductType newType) {

        if (newType == null) {
            throw new IllegalArgumentException("Product type is required.");
        }

        this.type = newType;

        touch();
    }

    /*
     * ============================================================================
     * RESUMO DA AULA
     * ============================================================================
     *
     * Nesta classe aprendemos:
     *
     * ✔ Entity
     *
     * ✔ Herança
     *
     * ✔ Encapsulamento
     *
     * ✔ Métodos de negócio
     *
     * ✔ Validação
     *
     * ✔ Value Object
     *
     * ✔ Enum
     *
     * ✔ touch()
     *
     * ✔ Soft Delete
     *
     * ============================================================================
     */

}
