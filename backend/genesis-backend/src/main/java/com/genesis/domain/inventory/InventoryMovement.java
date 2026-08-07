/**
 * ============================================================================
 * CLASSE: InventoryMovement
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Inventory
 *
 * RESPONSABILIDADE:
 *
 * Representa uma movimentação realizada no estoque.
 *
 * Diferente da classe Inventory, que representa
 * o estado atual do estoque, esta classe representa
 * um EVENTO ocorrido.
 *
 * Exemplos:
 *
 * • Entrada por compra
 * • Entrada por doação
 * • Saída por consumo
 * • Saída por perda
 * • Ajuste de estoque
 *
 * ============================================================================
 *
 * POR QUE ESTA CLASSE EXISTE?
 *
 * Imagine que hoje o estoque do café seja:
 *
 * 50 unidades.
 *
 * Amanhã alguém pergunta:
 *
 * "Como chegamos em 50?"
 *
 * Sem esta classe seria impossível responder.
 *
 * InventoryMovement registra todo o histórico.
 *
 * Ela funciona como um extrato bancário,
 * porém do estoque.
 *
 * ============================================================================
 *
 * QUEM UTILIZA ESTA CLASSE?
 *
 * ✔ RegisterInventoryMovementUseCase
 *
 * ✔ InventoryMovementRepository
 *
 * ✔ InventoryMovementPersistenceAdapter
 *
 * ============================================================================
 *
 * QUEM ESTA CLASSE UTILIZA?
 *
 * ✔ Inventory
 *
 * ✔ Quantity
 *
 * ✔ InventoryMovementType
 *
 * ✔ InventoryMovementReason
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
 * Classe base de todas as entidades.
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
 * Quantity representa uma quantidade.
 *
 * Utilizamos um Value Object
 * para centralizar todas as regras.
 */
import com.genesis.domain.shared.valueobject.Quantity;

/*
 * ============================================================================
 * ATRIBUTOS
 * ============================================================================
 */

public class InventoryMovement extends BaseEntity {

    /*
     * Estoque ao qual esta movimentação pertence.
     *
     * Exemplo:
     *
     * Produto:
     * Café
     *
     * Estoque:
     * 120 unidades
     *
     * Esta movimentação faz parte
     * do histórico desse estoque.
     */
    private final Inventory inventory;

    /*
     * Tipo da movimentação.
     *
     * Exemplos:
     *
     * ENTRY
     *
     * EXIT
     */

    private final InventoryMovementType type;

    /*
     * Motivo da movimentação.
     *
     * Exemplos:
     *
     * PURCHASE
     *
     * DONATION
     *
     * SALE
     *
     * CONSUMPTION
     */

    private final InventoryMovementReason reason;

    /*
     * Quantidade movimentada.
     *
     * Exemplo:
     *
     * Entrada:
     *
     * +20
     *
     * Saída:
     *
     * -5
     *
     * O sinal é definido pelo tipo
     * da movimentação.
     */

    private final Quantity quantity;

    /*
     * Observações da movimentação.
     *
     * Exemplo:
     *
     * Compra realizada no Atacadão.
     */

    private final String notes;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Utilizado sempre que uma nova movimentação
     * de estoque é registrada.
     *
     * Uma movimentação nunca deve ser alterada.
     *
     * Caso exista um erro,
     * uma nova movimentação deve ser criada.
     *
     * Isso garante a rastreabilidade.
     */

    public InventoryMovement(
            Inventory inventory,
            InventoryMovementType type,
            InventoryMovementReason reason,
            Quantity quantity,
            String notes) {

        /*
         * Toda movimentação pertence
         * obrigatoriamente a um estoque.
         */

        if (inventory == null) {
            throw new IllegalArgumentException("Inventory is required.");
        }

        /*
         * Toda movimentação precisa informar
         * se é uma entrada ou saída.
         */

        if (type == null) {
            throw new IllegalArgumentException("Movement type is required.");
        }

        /*
         * Também deve informar o motivo.
         */

        if (reason == null) {
            throw new IllegalArgumentException("Movement reason is required.");
        }

        if (quantity == null) {
            throw new IllegalArgumentException("Quantity is required.");
        }

        /*
         * Toda movimentação deve possuir
         * uma quantidade.
         */

        this.inventory = inventory;
        this.type = type;
        this.reason = reason;
        this.quantity = quantity;
        this.notes = notes;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     *
     * Apenas retornam informações.
     *
     * Como esta entidade é imutável,
     * não existem setters.
     */

    public Inventory getInventory() {
        return inventory;
    }

    public InventoryMovementType getType() {
        return type;
    }

    public InventoryMovementReason getReason() {
        return reason;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public String getNotes() {
        return notes;
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
     * ✔ Histórico
     *
     * ✔ Rastreabilidade
     *
     * ✔ Evento de domínio
     *
     * ✔ Encapsulamento
     *
     * ✔ Associação entre entidades
     *
     * ✔ Enum
     *
     * ✔ Imutabilidade
     *
     * ============================================================================
     */


}
