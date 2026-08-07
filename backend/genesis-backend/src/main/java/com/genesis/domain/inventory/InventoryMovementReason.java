/**
 * ============================================================================
 * ENUM: InventoryMovementReason
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Inventory
 *
 * RESPONSABILIDADE:
 *
 * Define o motivo pelo qual uma movimentação de estoque
 * foi realizada.
 *
 * Enquanto InventoryMovementType informa "o que aconteceu"
 * (Entrada, Saída ou Ajuste), este Enum informa "por que"
 * a movimentação aconteceu.
 *
 * Utilizar um Enum evita valores inválidos e centraliza
 * todos os motivos permitidos pelo sistema.
 * ============================================================================
 */
package com.genesis.domain.inventory;

/**
 * Enum que representa os possíveis motivos de uma
 * movimentação de estoque.
 */
public enum InventoryMovementReason {

    /**
     * Estoque inicial.
     *
     * Utilizado na criação do cadastro do produto,
     * quando é informado o saldo inicial.
     */
    INITIAL_STOCK,

    /**
     * Compra de mercadorias.
     *
     * Gera uma entrada de estoque.
     */
    PURCHASE,

    /**
     * Doação recebida.
     *
     * Gera uma entrada de estoque sem custo financeiro.
     */
    DONATION,

    /**
     * Consumo interno.
     *
     * Exemplo:
     * • Uso em escritório
     * • Uso na produção
     * • Material utilizado pela empresa
     */
    CONSUMPTION,

    /**
     * Perda de estoque.
     *
     * Exemplos:
     * • Produto vencido
     * • Produto danificado
     * • Extravio
     */
    LOSS,

    /**
     * Ajuste realizado após inventário físico.
     *
     * Utilizado quando a quantidade encontrada
     * é diferente da registrada no sistema.
     */
    INVENTORY_COUNT,

    /**
     * Outros motivos não contemplados pelos
     * valores anteriores.
     */
    OTHER

}
