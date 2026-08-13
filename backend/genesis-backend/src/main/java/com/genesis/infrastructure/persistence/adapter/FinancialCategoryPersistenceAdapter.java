/**
 * ============================================================================
 * CLASSE: FinancialCategoryPersistenceAdapter
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Adapter
 *
 * RESPONSABILIDADE:
 *
 * Implementar o contrato FinancialCategoryRepository
 * definido no Domain.
 *
 * Este Adapter conecta:
 *
 * Domain Repository
 *        ↓
 * JPA Repository
 *        ↓
 * Banco de Dados
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
 * Entidade de domínio.
 */
import com.genesis.domain.financial.FinancialCategory;

/*
 * Contrato definido pelo Domain.
 */
import com.genesis.domain.repository.FinancialCategoryRepository;

/*
 * Entidade utilizada pelo JPA.
 */
import com.genesis.infrastructure.persistence.entity.FinancialCategoryJpaEntity;

/*
 * Mapper responsável pela conversão
 * Domain ↔ JPA.
 */
import com.genesis.infrastructure.persistence.mapper.FinancialCategoryPersistenceMapper;

/*
 * Repository criado com Spring Data JPA.
 */
import com.genesis.infrastructure.persistence.repository.FinancialCategoryJpaRepository;

/*
 * Permite que o Spring registre esta classe
 * como componente de persistência.
 */
import org.springframework.stereotype.Repository;

/*
 * Lista de categorias.
 */
import java.util.List;

/*
 * Resultado opcional.
 */
import java.util.Optional;

/*
 * Identificador da categoria.
 */
import java.util.UUID;

/**
 * Adapter responsável pela persistência de categorias financeiras.
 */
@Repository
public class FinancialCategoryPersistenceAdapter
    implements FinancialCategoryRepository {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository que efetivamente conversa com o banco
     * através do Spring Data JPA.
     */
    private final FinancialCategoryJpaRepository jpaRepository;

    /*
     * Mapper responsável por converter
     * Domain ↔ JPA.
     */
    private final FinancialCategoryPersistenceMapper mapper;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * O Spring injeta automaticamente as dependências.
     */
    public FinancialCategoryPersistenceAdapter(
        FinancialCategoryJpaRepository jpaRepository,
        FinancialCategoryPersistenceMapper mapper) {

        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    /*
     * ============================================================================
     * MÉTODO: save()
     * ============================================================================
     *
     * Salva uma categoria no banco de dados.
     */
    @Override
    public FinancialCategory save(
        FinancialCategory category) {

        /*
         * Converte Domain → JPA.
         */
        FinancialCategoryJpaEntity entity =
            mapper.toJpaEntity(category);

        /*
         * Salva através do Spring Data JPA.
         */
        FinancialCategoryJpaEntity savedEntity =
            jpaRepository.save(entity);

        /*
         * Converte JPA → Domain.
         *
         * Assim o Use Case continua trabalhando
         * com a entidade de domínio.
         */
        return mapper.toDomain(savedEntity);
    }

    /*
     * ============================================================================
     * MÉTODO: findById()
     * ============================================================================
     *
     * Busca uma categoria pelo ID.
     */
    @Override
    public Optional<FinancialCategory> findById(UUID id) {

        return jpaRepository
            .findById(id)
            .map(mapper::toDomain);
    }

    /*
     * ============================================================================
     * MÉTODO: findByName()
     * ============================================================================
     *
     * Busca uma categoria pelo nome.
     */
    @Override
    public Optional<FinancialCategory> findByName(
        String name) {

        return jpaRepository
            .findByName(name)
            .map(mapper::toDomain);
    }

    /*
     * ============================================================================
     * MÉTODO: findAll()
     * ============================================================================
     *
     * Busca todas as categorias.
     */
    @Override
    public List<FinancialCategory> findAll() {

        return jpaRepository
            .findAll()
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Domain:
     *
     * FinancialCategoryRepository
     *
     *          ↓
     *
     * Adapter:
     *
     * FinancialCategoryPersistenceAdapter
     *
     *          ↓
     *
     * JPA:
     *
     * FinancialCategoryJpaRepository
     *
     *          ↓
     *
     * PostgreSQL
     *
     * ============================================================================
     */
}
