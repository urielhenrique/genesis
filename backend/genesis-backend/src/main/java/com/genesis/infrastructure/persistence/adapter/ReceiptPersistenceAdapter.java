/**
 * ============================================================================
 * CLASSE: ReceiptPersistenceAdapter
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Adapter
 *
 * RESPONSABILIDADE:
 *
 * Implementar o contrato ReceiptRepository definido no Domain.
 *
 * ============================================================================
 */
package com.genesis.infrastructure.persistence.adapter;

import com.genesis.domain.receipt.Receipt;
import com.genesis.domain.repository.ReceiptRepository;
import com.genesis.infrastructure.persistence.entity.ReceiptJpaEntity;
import com.genesis.infrastructure.persistence.mapper.ReceiptPersistenceMapper;
import com.genesis.infrastructure.persistence.repository.ReceiptJpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Adapter responsável pela persistência de comprovantes.
 */
@Repository
public class ReceiptPersistenceAdapter
    implements ReceiptRepository {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository que efetivamente acessa o banco.
     */
    private final ReceiptJpaRepository jpaRepository;

    /*
     * Mapper responsável pelas conversões.
     */
    private final ReceiptPersistenceMapper mapper;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public ReceiptPersistenceAdapter(
        ReceiptJpaRepository jpaRepository,
        ReceiptPersistenceMapper mapper) {

        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    /*
     * ============================================================================
     * MÉTODO: save()
     * ============================================================================
     *
     * Salva ou atualiza um comprovante.
     */
    @Override
    public Receipt save(Receipt receipt) {

        /*
         * Domain → JPA.
         */
        ReceiptJpaEntity entity =
            mapper.toJpaEntity(receipt);

        /*
         * Persiste no banco.
         */
        ReceiptJpaEntity savedEntity =
            jpaRepository.save(entity);

        /*
         * JPA → Domain.
         */
        return mapper.toDomain(savedEntity);
    }

    /*
     * ============================================================================
     * MÉTODO: findById()
     * ============================================================================
     *
     * Busca um comprovante pelo ID.
     */
    @Override
    public Optional<Receipt> findById(UUID id) {

        return jpaRepository
            .findById(id)
            .map(mapper::toDomain);
    }

    /*
     * ============================================================================
     * MÉTODO: findByFinancialTransactionId()
     * ============================================================================
     *
     * Busca todos os comprovantes associados
     * a uma movimentação financeira.
     */
    @Override
    public List<Receipt> findByFinancialTransactionId(
        UUID financialTransactionId) {

        return jpaRepository
            .findByFinancialTransactionId(financialTransactionId)
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    /*
     * ============================================================================
     * MÉTODO: delete()
     * ============================================================================
     *
     * Remove um comprovante.
     */
    @Override
    public void delete(Receipt receipt) {

        /*
         * Domain → JPA.
         */
        ReceiptJpaEntity entity =
            mapper.toJpaEntity(receipt);

        /*
         * Remove através do Spring Data JPA.
         */
        jpaRepository.delete(entity);
    }
}
