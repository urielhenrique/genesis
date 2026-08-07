/**
 * ============================================================================
 * INTERFACE: InventoryMovementRepository
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Repository
 *
 * RESPONSABILIDADE:
 *
 * Define o contrato de persistência das movimentações
 * de estoque.
 *
 * Cada movimentação representa um evento ocorrido
 * no estoque de um produto.
 *
 * Assim como os demais repositórios do domínio,
 * esta interface não conhece Spring Boot, JPA ou
 * banco de dados.
 *
 * Apenas define as operações que devem existir.
 *
 * ============================================================================
 *
 * QUEM UTILIZA ESTA INTERFACE?
 *
 * • RegisterInventoryMovementUseCase
 *
 * QUEM IMPLEMENTA ESTA INTERFACE?
 *
 * • InventoryMovementPersistenceAdapter
 *
 * ============================================================================
 */
package com.genesis.domain.repository;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Entidade que representa uma movimentação de estoque.
 *
 * Cada objeto registra um evento ocorrido no estoque,
 * permitindo manter todo o histórico de entradas,
 * saídas e ajustes.
 */
import com.genesis.domain.inventory.InventoryMovement;

/**
 * Contrato de persistência da entidade InventoryMovement.
 */
public interface InventoryMovementRepository {

    /**
     * Salva uma movimentação de estoque.
     *
     * Normalmente este método é chamado após
     * uma entrada, saída ou ajuste de estoque,
     * garantindo que o histórico fique registrado.
     *
     * @param movement Movimentação que será persistida.
     * @return Movimentação salva.
     */
    InventoryMovement save(InventoryMovement movement);

}
