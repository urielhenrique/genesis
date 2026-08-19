/**
 * ============================================================================
 * INTERFACE: ProductRepository
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Repository
 *
 * RESPONSABILIDADE:
 *
 * Define o contrato de acesso aos dados dos produtos.
 *
 * Esta interface pertence ao Domínio e NÃO sabe onde os
 * dados estão armazenados.
 *
 * O produto pode estar em:
 *
 * • PostgreSQL
 * • MySQL
 * • Oracle
 * • MongoDB
 * • Arquivo
 * • API Externa
 *
 * O domínio não conhece nenhuma dessas tecnologias.
 *
 * Apenas define quais operações precisam existir.
 *
 * ============================================================================
 *
 * QUEM UTILIZA ESTA INTERFACE?
 *
 * • Use Cases
 * • Services da camada Application
 *
 * QUEM IMPLEMENTA ESTA INTERFACE?
 *
 * • ProductPersistenceAdapter
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
 * Entidade de domínio que representa um produto.
 */
import com.genesis.domain.product.Product;

/*
 * Lista utilizada para retornar vários produtos.
 */
import java.util.List;

/*
 * Optional representa um valor que pode existir ou não.
 *
 * Evita retornar null e torna o código mais seguro.
 */
import java.util.Optional;

/*
 * UUID representa o identificador único do produto.
 */
import java.util.UUID;

/**
 * Contrato de persistência da entidade Product.
 */
public interface ProductRepository {

    /**
     * Salva um produto.
     *
     * Pode ser utilizado tanto para:
     *
     * • Criar um novo produto.
     * • Atualizar um produto existente.
     *
     * @param product Produto a ser salvo.
     * @return Produto persistido.
     */
    Product save(Product product);

    /**
     * Procura um produto pelo seu identificador.
     *
     * @param id Identificador único do produto.
     * @return Optional contendo o produto, caso exista.
     */
    Optional<Product> findById(UUID id);

    Optional<Product> findByIdIncludingInactive(UUID id);

    /**
     * Procura um produto pelo nome.
     *
     * @param name Nome do produto.
     * @return Optional contendo o produto encontrado.
     */
    Optional<Product> findByName(String name);

    /**
     * Retorna todos os produtos cadastrados.
     *
     * @return Lista de produtos.
     */
    List<Product> findAll();

}
