/**
 * ============================================================================
 * CLASSE: FinancialTransactionPersistenceAdapter
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Adapter
 *
 * RESPONSABILIDADE:
 *
 * Implementar o contrato FinancialTransactionRepository
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
 * Entidade de domínio da movimentação financeira.
 */
import com.genesis.domain.financial.FinancialTransaction;

/*
 * Entidade de domínio da categoria financeira.
 */
import com.genesis.domain.financial.FinancialCategory;

/*
 * Exceção lançada quando a categoria relacionada
 * não é encontrada.
 */
import com.genesis.domain.exception.FinancialCategoryNotFoundException;

/*
 * Contrato definido pelo Domain.
 */
import com.genesis.domain.repository.FinancialTransactionRepository;

/*
 * Repository utilizado para localizar a categoria.
 */
import com.genesis.domain.repository.FinancialCategoryRepository;

/*
 * Entidade utilizada pelo JPA.
 */
import com.genesis.infrastructure.persistence.entity.FinancialTransactionJpaEntity;

/*
 * Mapper responsável pela conversão
 * Domain ↔ JPA.
 */
import com.genesis.infrastructure.persistence.mapper.FinancialTransactionPersistenceMapper;

/*
 * Repository criado com Spring Data JPA.
 */
import com.genesis.infrastructure.persistence.repository.FinancialTransactionJpaRepository;

/*
 * Permite que o Spring registre esta classe
 * como componente de persistência.
 */
import org.springframework.stereotype.Repository;

/*
 * Lista de movimentações.
 */
import java.util.List;

/*
 * Resultado opcional.
 */
import java.util.Optional;

/*
 * Identificador da movimentação.
 */
import java.util.UUID;

/**
 * Adapter responsável pela persistência de movimentações financeiras.
 */
@Repository
public class FinancialTransactionPersistenceAdapter
    implements FinancialTransactionRepository {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository que conversa com o banco
     * através do Spring Data JPA.
     */
    private final FinancialTransactionJpaRepository jpaRepository;

    /*
     * Mapper responsável por converter
     * Domain ↔ JPA.
     */
    private final FinancialTransactionPersistenceMapper mapper;

    /*
     * Repository utilizado para localizar a categoria
     * relacionada à movimentação.
     */
    private final FinancialCategoryRepository categoryRepository;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * O Spring injeta automaticamente as dependências.
     */
    public FinancialTransactionPersistenceAdapter(
        FinancialTransactionJpaRepository jpaRepository,
        FinancialTransactionPersistenceMapper mapper,
        FinancialCategoryRepository categoryRepository) {

        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    /*
     * ============================================================================
     * MÉTODO: save()
     * ============================================================================
     *
     * Salva uma movimentação financeira no banco.
     */
    @Override
    public FinancialTransaction save(
        FinancialTransaction transaction) {

        /*
         * Converte Domain → JPA.
         */
        FinancialTransactionJpaEntity entity =
            mapper.toJpaEntity(transaction);

        /*
         * Salva através do Spring Data JPA.
         */
        FinancialTransactionJpaEntity savedEntity =
            jpaRepository.save(entity);

        /*
         * Recupera a categoria necessária para
         * reconstruir a entidade de domínio.
         */
        FinancialCategory category =
            categoryRepository
                .findById(savedEntity.getCategoryId())
                .orElseThrow(() ->
                    new FinancialCategoryNotFoundException(
                        savedEntity.getCategoryId()
                    )
                );

        /*
         * Converte JPA → Domain.
         */
        return mapper.toDomain(
            savedEntity,
            category
        );
    }

    /*
     * ============================================================================
     * MÉTODO: findById()
     * ============================================================================
     *
     * Busca uma movimentação pelo ID.
     */
    @Override
    public Optional<FinancialTransaction> findById(
        UUID id) {

        return jpaRepository
            .findByIdAndActiveTrue(id)
            .map(entity ->
                categoryRepository
                    .findById(entity.getCategoryId())
                    .map(category ->
                        mapper.toDomain(
                            entity,
                            category
                        )
                    )
                    .orElseThrow(() ->
                        new FinancialCategoryNotFoundException(
                            entity.getCategoryId()
                        )
                    )
            );
    }

    /*
     * ============================================================================
     * MÉTODO: findAll()
     * ============================================================================
     *
     * Busca todas as movimentações financeiras.
     */
    @Override
    public List<FinancialTransaction> findAll() {

        return jpaRepository
            .findAllByActiveTrue()
            .stream()
            .map(entity -> {

                /*
                 * Busca a categoria associada.
                 */
                FinancialCategory category =
                    categoryRepository
                        .findById(entity.getCategoryId())
                        .orElseThrow(() ->
                            new FinancialCategoryNotFoundException(
                                entity.getCategoryId()
                            )
                        );

                /*
                 * Converte JPA → Domain.
                 */
                return mapper.toDomain(
                    entity,
                    category
                );
            })
            .toList();
    }

    /*
     * ============================================================================
     * MÉTODO: delete()
     * ============================================================================
     *
     * Remove uma movimentação do banco.
     */
    @Override
    public void delete(
        FinancialTransaction transaction) {

        /*
         * Converte Domain → JPA.
         */
        FinancialTransactionJpaEntity entity =
            mapper.toJpaEntity(transaction);

        /*
         * Remove através do Spring Data JPA.
         */
        jpaRepository.delete(entity);
    }

    /*
     * ============================================================================
     * RESUMO
     * ============================================================================
     *
     * Domain:
     *
     * FinancialTransactionRepository
     *
     *          ↓
     *
     * Adapter:
     *
     * FinancialTransactionPersistenceAdapter
     *
     *          ↓
     *
     * JPA:
     *
     * FinancialTransactionJpaRepository
     *
     *          ↓
     *
     * PostgreSQL
     *
     * ============================================================================
     */
}
