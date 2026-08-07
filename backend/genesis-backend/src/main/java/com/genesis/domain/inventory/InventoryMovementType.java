/**
 * ============================================================================
 * ENUM: InventoryMovementType
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Inventory
 *
 * RESPONSABILIDADE:
 *
 * Define o tipo de uma movimentação de estoque.
 *
 * Este enum é utilizado para identificar se uma movimentação
 * adiciona, remove ou apenas ajusta a quantidade em estoque.
 *
 * Ao utilizar um Enum evitamos "Strings mágicas", como:
 *
 * "ENTRY"
 * "EXIT"
 *
 * Isso reduz erros de digitação e torna o código mais seguro.
 * ============================================================================
 */
package com.genesis.domain.inventory;

/**
 * Enum que representa os tipos possíveis de movimentação de estoque.
 */
public enum InventoryMovementType {

    /**
     * Entrada de estoque.
     *
     * Exemplos:
     * • Compra
     * • Doação
     * • Devolução de cliente
     * • Produção concluída
     */
    ENTRY,

    /**
     * Saída de estoque.
     *
     * Exemplos:
     * • Venda
     * • Consumo interno
     * • Perda
     * • Produto vencido
     */
    EXIT,

    /**
     * Ajuste de estoque.
     *
     * Utilizado quando é necessário corrigir a quantidade
     * registrada no sistema após uma conferência ou inventário.
     *
     * Exemplos:
     * • Inventário físico
     * • Correção de cadastro
     * • Ajuste administrativo
     */
    ADJUSTMENT

}
