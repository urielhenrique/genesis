/**
 * ============================================================================
 * INTERFACE: InventoryRepository
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Repository
 *
 * RESPONSABILIDADE:
 *
 * Define o contrato de persistência da entidade Inventory.
 *
 * Assim como todo Repository do domínio, esta interface
 * não conhece banco de dados, JPA ou Spring Boot.
 *
 * Ela apenas informa quais operações de persistência
 * devem existir para que a aplicação consiga trabalhar
 * com o estoque.
 *
 * ============================================================================
 *
 * QUEM UTILIZA ESTA INTERFACE?
 *
 * • RegisterInventoryMovementUseCase
 * • Outros Use Cases relacionados ao estoque
 *
 * QUEM IMPLEMENTA ESTA INTERFACE?
 *
 * • InventoryPersistenceAdapter
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
 * Entidade de domínio que representa o estoque
 * de um produto.
 */
import com.genesis.domain.inventory.Inventory;

/*
 * Lista utilizada para retornar vários estoques.
 */
import java.util.List;

/*
 * Optional representa um resultado que pode
 * existir ou não.
 *
 * Evita o uso de null.
 */
import java.util.Optional;

/*
 * Identificador único do produto.
 *
 * O estoque é localizado através do UUID
 * do produto associado.
 */
import java.util.UUID;

/**
 * Contrato de persistência da entidade Inventory.
 */
public interface InventoryRepository {

    /**
     * Salva um estoque.
     *
     * Pode ser utilizado para criar um novo estoque
     * ou atualizar um estoque existente.
     *
     * @param inventory Estoque que será persistido.
     * @return Estoque salvo.
     */
    Inventory save(Inventory inventory);

    /**
     * Procura o estoque de um determinado produto.
     *
     * Como cada produto possui apenas um estoque,
     * retornamos um Optional.
     *
     * @param productId Identificador do produto.
     * @return Optional contendo o estoque, caso exista.
     */
    Optional<Inventory> findByProductId(UUID productId);

    /**
     * Retorna todos os estoques cadastrados.
     *
     * @return Lista de estoques.
     */
    List<Inventory> findAll();

}
