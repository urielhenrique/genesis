/**
 * ============================================================================
 * CLASSE: EventPersistenceAdapter
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Adapter
 *
 * RESPONSABILIDADE:
 *
 * Implementar o contrato EventRepository definido no Domain.
 *
 * O Adapter faz a ponte entre:
 *
 * Domain
 *   ↓
 * EventRepository
 *   ↓
 * EventPersistenceAdapter
 *   ↓
 * EventJpaRepository
 *   ↓
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
import com.genesis.domain.event.Event;

/*
 * Contrato definido pelo Domain.
 */
import com.genesis.domain.repository.EventRepository;

/*
 * Entidade utilizada pelo JPA.
 */
import com.genesis.infrastructure.persistence.entity.EventJpaEntity;

/*
 * Mapper responsável pela conversão
 * Domain ↔ JPA.
 */
import com.genesis.infrastructure.persistence.mapper.EventPersistenceMapper;

/*
 * Repository do Spring Data JPA.
 */
import com.genesis.infrastructure.persistence.repository.EventJpaRepository;

/*
 * Permite que o Spring registre o Adapter
 * como componente de persistência.
 */
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Adapter responsável pela persistência de eventos.
 */
@Repository
public class EventPersistenceAdapter
    implements EventRepository {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Repository que efetivamente acessa o banco.
     */
    private final EventJpaRepository jpaRepository;

    /*
     * Mapper responsável pelas conversões.
     */
    private final EventPersistenceMapper mapper;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public EventPersistenceAdapter(
        EventJpaRepository jpaRepository,
        EventPersistenceMapper mapper) {

        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    /*
     * ============================================================================
     * MÉTODO: save()
     * ============================================================================
     *
     * Salva ou atualiza um evento.
     */
    @Override
    public Event save(Event event) {

        /*
         * Domain → JPA.
         */
        EventJpaEntity entity =
            mapper.toJpaEntity(event);

        /*
         * Persiste no banco.
         */
        EventJpaEntity savedEntity =
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
     * Busca um evento pelo ID.
     */
    @Override
    public Optional<Event> findById(UUID id) {

        return jpaRepository
            .findByIdAndActiveTrue(id)
            .map(mapper::toDomain);
    }

    /*
     * ============================================================================
     * MÉTODO: findAll()
     * ============================================================================
     *
     * Busca todos os eventos.
     */
    @Override
    public List<Event> findAll() {

        return jpaRepository
            .findAllByActiveTrue()
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    /*
     * ============================================================================
     * MÉTODO: delete()
     * ============================================================================
     *
     * Remove um evento.
     */
    @Override
    public void delete(Event event) {

        /*
         * Domain → JPA.
         */
        EventJpaEntity entity =
            mapper.toJpaEntity(event);

        /*
         * Remove através do Spring Data JPA.
         */
        jpaRepository.delete(entity);
    }
}
