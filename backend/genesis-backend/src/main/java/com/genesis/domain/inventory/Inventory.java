/**
 * ============================================================================
 * CLASSE: Inventory
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Inventory
 *
 * RESPONSABILIDADE:
 *
 * Representa o estoque de um produto.
 *
 * Diferente do Product, que representa o cadastro,
 * Inventory representa a quantidade disponível.
 *
 * Exemplos:
 *
 * Produto:
 * Café
 *
 * Estoque:
 * 150 unidades
 *
 * ============================================================================
 *
 * O QUE É UMA ENTITY?
 *
 * Inventory é uma Entity porque possui identidade (UUID),
 * herdada da BaseEntity.
 *
 * Mesmo que a quantidade mude, continua sendo o mesmo estoque.
 *
 * ============================================================================
 *
 * RESPONSABILIDADES DA CLASSE
 *
 * ✔ Controlar a quantidade disponível.
 *
 * ✔ Permitir entrada de estoque.
 *
 * ✔ Permitir saída de estoque.
 *
 * ✔ Impedir estoque negativo.
 *
 * ✔ Atualizar a data da última alteração.
 *
 * ============================================================================
 */
package com.genesis.domain.inventory;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Exceção específica do domínio.
 *
 * É lançada quando alguém tenta retirar mais itens
 * do que existem no estoque.
 */
import com.genesis.domain.exception.InsufficientInventoryException;

/*
 * Representa o produto ao qual este estoque pertence.
 *
 * Um estoque sempre está associado a um único produto.
 */
import com.genesis.domain.product.Product;

/*
 * Classe base das entidades do domínio.
 *
 * Fornece:
 *
 * • UUID
 * • createdAt
 * • updatedAt
 * • touch()
 */
import com.genesis.domain.shared.entity.BaseEntity;

/*
 * Value Object responsável por representar quantidades.
 *
 * Centraliza todas as regras relacionadas à quantidade.
 */
import com.genesis.domain.shared.valueobject.Quantity;

public class Inventory extends BaseEntity {

    /*
     * ========================================================================
     * ATRIBUTOS
     * ========================================================================
     */

    /*
     * Produto ao qual este estoque pertence.
     *
     * Exemplo:
     *
     * Produto:
     * Café
     */
    private Product product;

    /*
     * Quantidade disponível.
     *
     * Exemplo:
     *
     * 150 unidades.
     */
    private Quantity quantity;

    /*
     * ========================================================================
     * CONSTRUTOR
     * ========================================================================
     *
     * Utilizado para criar um NOVO estoque.
     *
     * O BaseEntity gera automaticamente:
     *
     * • UUID
     * • createdAt
     * • updatedAt
     */

    public Inventory(
        Product product,
        Quantity quantity) {

        /*
         * Todo estoque deve estar associado
         * a um produto.
         */

        if (product == null) {
            throw new IllegalArgumentException("Product is required.");
        }

        /*
         * Todo estoque deve possuir
         * uma quantidade inicial.
         */

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity is required.");
        }

        this.product = product;
        this.quantity = quantity;
    }

    /*
     * ========================================================================
     * GETTERS
     * ========================================================================
     *
     * Apenas devolvem informações.
     *
     * Nunca alteram o estado da entidade.
     */

    public Product getProduct() {
        return product;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    /*
     * ============================================================================
     * MÉTODO increase()
     * ============================================================================
     *
     * RESPONSABILIDADE:
     *
     * Adicionar itens ao estoque.
     *
     * Exemplos:
     *
     * • Compra
     * • Doação
     * • Ajuste positivo
     *
     * FLUXO:
     *
     * 1. Valida a quantidade.
     * 2. Soma ao estoque atual.
     * 3. Atualiza updatedAt.
     */

    public void increase(Quantity quantity) {

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity is required.");
        }

        if (!quantity.isPositive()) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        /*
         * Como Quantity é imutável,
         * o método add() retorna um novo objeto.
         *
         * Por isso substituímos a referência atual.
         */

        this.quantity = this.quantity.add(quantity);

        touch();
    }

    /*
     * ============================================================================
     * MÉTODO decrease()
     * ============================================================================
     *
     * RESPONSABILIDADE:
     *
     * Retirar itens do estoque.
     *
     * Exemplos:
     *
     * • Venda
     * • Consumo
     * • Perda
     * • Ajuste negativo
     *
     * FLUXO:
     *
     * 1. Valida a quantidade.
     * 2. Verifica saldo disponível.
     * 3. Remove os itens.
     * 4. Atualiza updatedAt.
     */

    public void decrease(Quantity quantity) {

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity is required.");
        }

        /*
         * Nunca permitimos estoque negativo.
         *
         * Caso a quantidade solicitada seja maior
         * que a disponível, lançamos uma exceção
         * específica do domínio.
         */

        if (this.quantity.getValue().compareTo(quantity.getValue()) < 0) {
            throw new InsufficientInventoryException(
                product.getName(),
                this.quantity.getValue().intValue(),
                quantity.getValue().intValue()
            );
        }

        this.quantity = this.quantity.subtract(quantity);

        touch();
    }

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Utilizado para reconstruir uma entidade
     * vinda do banco de dados.
     *
     * Exemplo:
     *
     * PostgreSQL
     *      ↓
     * JPA Entity
     *      ↓
     * Inventory
     *
     * Nesse caso utilizamos o UUID existente.
     */

    public Inventory(
        java.util.UUID id,
        java.time.LocalDateTime createdAt,
        java.time.LocalDateTime updatedAt,
        Product product,
        Quantity quantity) {

        super(id, createdAt, updatedAt);

        if (product == null) {
            throw new IllegalArgumentException("Product is required.");
        }

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity is required.");
        }

        this.product = product;
        this.quantity = quantity;
    }

    /*
     * ============================================================================
     * RESUMO DA AULA
     * ============================================================================
     *
     * Nesta classe aprendemos:
     *
     * ✔ Entity
     * ✔ Agregado simples
     * ✔ Associação entre entidades
     * ✔ Encapsulamento
     * ✔ Métodos de negócio
     * ✔ Exceções de domínio
     * ✔ Value Object
     * ✔ Imutabilidade
     * ✔ touch()
     * ✔ Regras de negócio
     *
     * ============================================================================
     */

}
