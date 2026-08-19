/**
 * ============================================================================
 * CLASSE: ProductPersistenceAdapter
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Adapter
 *
 * RESPONSABILIDADE:
 *
 * Implementar o contrato ProductRepository definido
 * pela camada de Domínio.
 *
 * Esta classe faz a ponte entre o Domínio e a
 * Persistência.
 *
 * Ela converte entidades do Domínio em entidades JPA
 * antes de salvar no banco e realiza o caminho inverso
 * quando recupera informações.
 *
 * ============================================================================
 *
 * FLUXO:
 *
 * Product (Domínio)
 *        ↓
 * ProductPersistenceMapper
 *        ↓
 * ProductJpaEntity
 *        ↓
 * ProductJpaRepository
 *        ↓
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
 * Entidade de domínio Product.
 */
import com.genesis.domain.product.Product;

/*
 * Contrato de persistência definido pelo Domínio.
 */
import com.genesis.domain.repository.ProductRepository;

/*
 * Entidade utilizada pelo JPA.
 */
import com.genesis.infrastructure.persistence.entity.ProductJpaEntity;

/*
 * Responsável por converter Product
 * ⇄ ProductJpaEntity.
 */
import com.genesis.infrastructure.persistence.mapper.ProductPersistenceMapper;

/*
 * Repositório JPA responsável por acessar
 * o banco de dados.
 */
import com.genesis.infrastructure.persistence.repository.ProductJpaRepository;

/*
 * Registra esta classe como um Repository
 * gerenciado pelo Spring Boot.
 */
import org.springframework.stereotype.Repository;

/*
 * Lista utilizada no método findAll().
 */
import java.util.List;

/*
 * Representa um resultado que pode existir
 * ou não.
 */
import java.util.Optional;

/*
 * Identificador único do produto.
 */
import java.util.UUID;

@Repository
public class ProductPersistenceAdapter implements ProductRepository {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repositório JPA responsável pelo acesso
     * ao banco de dados.
     */
    private final ProductJpaRepository jpaRepository;

    /*
     * Responsável por converter objetos entre
     * Domínio e Persistência.
     */
    private final ProductPersistenceMapper mapper;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * O Spring Boot injeta automaticamente
     * as dependências necessárias.
     */
    public ProductPersistenceAdapter(
        ProductJpaRepository jpaRepository,
        ProductPersistenceMapper mapper) {

        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    /*
     * ============================================================================
     * MÉTODO: save()
     * ============================================================================
     *
     * Responsabilidade:
     *
     * Salvar um produto no banco de dados.
     *
     * Fluxo:
     *
     * Product
     *      ↓
     * ProductJpaEntity
     *      ↓
     * Banco
     *      ↓
     * ProductJpaEntity
     *      ↓
     * Product
     */
    @Override
    public Product save(Product product) {

        ProductJpaEntity entity = mapper.toJpaEntity(product);

        ProductJpaEntity savedEntity = jpaRepository.save(entity);

        return mapper.toDomain(savedEntity);
    }

    /*
     * ============================================================================
     * MÉTODO: findById()
     * ============================================================================
     *
     * Procura um produto pelo UUID.
     */
    @Override
    public Optional<Product> findById(UUID id) {

        return jpaRepository
            .findByIdAndActiveTrue(id)
            .map(mapper::toDomain);
    }

    /*
     * ============================================================================
     * MÉTODO: findByName()
     * ============================================================================
     *
     * Procura um produto pelo nome.
     */
    @Override
    public Optional<Product> findByName(String name) {

        return jpaRepository
            .findByNameAndActiveTrue(name)
            .map(mapper::toDomain);
    }

    /*
     * ============================================================================
     * MÉTODO: findAll()
     * ============================================================================
     *
     * Retorna todos os produtos cadastrados.
     *
     * Cada ProductJpaEntity é convertido para
     * Product antes de ser devolvido ao Domínio.
     */
    @Override
    public List<Product> findAll() {

        return jpaRepository
            .findAllByActiveTrue()
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public Optional<Product> findByIdIncludingInactive(UUID id) {

        return jpaRepository
            .findById(id)
            .map(mapper::toDomain);
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
     * ✔ Spring Data JPA
     * ✔ @Repository
     * ✔ Clean Architecture
     *
     * ============================================================================
     */
}
