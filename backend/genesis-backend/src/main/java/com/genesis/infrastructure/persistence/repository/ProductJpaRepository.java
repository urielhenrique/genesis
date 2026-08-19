/**
 * ============================================================================
 * INTERFACE: ProductJpaRepository
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Repository
 *
 * RESPONSABILIDADE:
 *
 * Realizar o acesso ao banco de dados utilizando
 * Spring Data JPA.
 *
 * Diferente do ProductRepository (Domínio),
 * esta interface conhece JPA e a entidade de persistência
 * ProductJpaEntity.
 *
 * O Spring Boot cria automaticamente a implementação
 * desta interface em tempo de execução.
 *
 * ============================================================================
 *
 * QUEM UTILIZA ESTA INTERFACE?
 *
 * • ProductPersistenceAdapter
 *
 * QUEM IMPLEMENTA ESTA INTERFACE?
 *
 * • Spring Data JPA (automaticamente)
 *
 * ============================================================================
 */
package com.genesis.infrastructure.persistence.repository;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

/*
 * Entidade JPA que representa a tabela Product
 * no banco de dados.
 */
import com.genesis.infrastructure.persistence.entity.ProductJpaEntity;

/*
 * Interface do Spring Data JPA.
 *
 * Fornece automaticamente operações como:
 *
 * • save()
 * • findById()
 * • findAll()
 * • delete()
 * • existsById()
 *
 * Sem necessidade de implementação manual.
 */
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Optional representa um resultado que pode
 * existir ou não.
 */
import java.util.Optional;

/*
 * Identificador único da entidade.
 */
import java.util.UUID;
import java.util.List;
import java.util.Optional;

/**
 * Repositório JPA da entidade ProductJpaEntity.
 */
public interface ProductJpaRepository
    extends JpaRepository<ProductJpaEntity, UUID> {

    /*
     * Busca somente produtos ativos pelo ID.
     */
    Optional<ProductJpaEntity> findByIdAndActiveTrue(
        UUID id
    );

    /*
     * Lista somente produtos ativos.
     */
    List<ProductJpaEntity> findAllByActiveTrue();

    /*
     * Busca um produto ativo pelo nome.
     */
    Optional<ProductJpaEntity> findByNameAndActiveTrue(
        String name
    );

}
