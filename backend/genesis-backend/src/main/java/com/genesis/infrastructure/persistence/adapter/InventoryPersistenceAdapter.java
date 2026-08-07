/**
 * ============================================================================
 * CLASSE: InventoryPersistenceAdapter
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Adapter
 *
 * RESPONSABILIDADE:
 *
 * Implementar o contrato InventoryRepository definido
 * pela camada de Domínio.
 *
 * Esta classe faz a ponte entre o Domínio e o banco
 * de dados, utilizando o Mapper para converter objetos
 * e o JpaRepository para persistir as informações.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * Inventory (Domínio)
 *         ↓
 * InventoryPersistenceMapper
 *         ↓
 * InventoryJpaEntity
 *         ↓
 * InventoryJpaRepository
 *         ↓
 * PostgreSQL
 *
 * ============================================================================
 */
package com.genesis.infrastructure.persistence.adapter;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Exceção lançada quando o produto associado
 * ao estoque não é encontrado.
 */
import com.genesis.domain.exception.ProductNotFoundException;

/*
 * Entidade de domínio Inventory.
 */
import com.genesis.domain.inventory.Inventory;

/*
 * Entidade de domínio Product.
 */
import com.genesis.domain.product.Product;

/*
 * Contrato de persistência do estoque.
 */
import com.genesis.domain.repository.InventoryRepository;

/*
 * Contrato de persistência do produto.
 *
 * Utilizado para reconstruir a entidade Inventory.
 */
import com.genesis.domain.repository.ProductRepository;

/*
 * Entidade JPA utilizada na persistência.
 */
import com.genesis.infrastructure.persistence.entity.InventoryJpaEntity;

/*
 * Responsável por converter Inventory
 * ⇄ InventoryJpaEntity.
 */
import com.genesis.infrastructure.persistence.mapper.InventoryPersistenceMapper;

/*
 * Repositório JPA responsável pelo acesso
 * à tabela inventory.
 */
import com.genesis.infrastructure.persistence.repository.InventoryJpaRepository;

/*
 * Registra esta classe como um Repository
 * gerenciado pelo Spring Boot.
 */
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InventoryPersistenceAdapter implements InventoryRepository {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repositório JPA responsável pela persistência
     * do estoque.
     */
    private final InventoryJpaRepository jpaRepository;

    /*
     * Responsável pela conversão entre
     * Domínio e Persistência.
     */
    private final InventoryPersistenceMapper mapper;

    /*
     * Repositório utilizado para localizar
     * o produto associado ao estoque.
     */
    private final ProductRepository productRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * O Spring injeta automaticamente todas
     * as dependências necessárias.
     */
    public InventoryPersistenceAdapter(
        InventoryJpaRepository jpaRepository,
        InventoryPersistenceMapper mapper,
        ProductRepository productRepository) {

        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
        this.productRepository = productRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: save()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Persistir um estoque no banco de dados.
     *
     * Fluxo:
     *
     * Inventory
     *      ↓
     * InventoryJpaEntity
     *      ↓
     * Banco
     */
    @Override
    public Inventory save(Inventory inventory) {

        InventoryJpaEntity entity = mapper.toJpaEntity(inventory);

        jpaRepository.save(entity);

        return inventory;
    }

    /*
     * ============================================================================
     * MÉTODO: findByProductId()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Localizar o estoque de um produto.
     *
     * Como a tabela inventory armazena apenas
     * o UUID do produto, é necessário buscar
     * também a entidade Product para reconstruir
     * completamente o Inventory.
     */
    @Override
    public Optional<Inventory> findByProductId(UUID productId) {

        return jpaRepository
            .findByProductId(productId)
            .flatMap(entity ->
                productRepository.findById(entity.getProductId())
                    .map(product -> mapper.toDomain(entity, product))
            );
    }

    /*
     * ============================================================================
     * MÉTODO: findAll()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Retornar todos os estoques cadastrados.
     *
     * Cada InventoryJpaEntity é convertido
     * para uma entidade de domínio.
     */
    @Override
    public List<Inventory> findAll() {

        return jpaRepository.findAll()
            .stream()
            .map(entity -> {

                Product product = productRepository
                    .findById(entity.getProductId())
                    .orElseThrow(() ->
                        new ProductNotFoundException(entity.getProductId()));

                return mapper.toDomain(entity, product);
            })
            .toList();
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Conceitos aprendidos:
     *
     * ✔ Adapter
     * ✔ Repository Pattern
     * ✔ Implementação de Interface
     * ✔ Conversão entre Domínio e Persistência
     * ✔ Optional
     * ✔ Stream API
     * ✔ @Repository
     * ✔ Clean Architecture
     *
     * ============================================================================
     */
}
