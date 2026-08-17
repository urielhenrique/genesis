/**
 * ============================================================================
 * CLASSE: EventPersistenceMapper
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Mapper
 *
 * RESPONSABILIDADE:
 *
 * Converter entre:
 *
 * Event
 *     ↕
 * EventJpaEntity
 *
 * O Domain não conhece JPA.
 *
 * ============================================================================
 */
package com.genesis.infrastructure.persistence.mapper;

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
 * Entidade utilizada pelo JPA/Hibernate.
 */
import com.genesis.infrastructure.persistence.entity.EventJpaEntity;

/*
 * Permite que o Spring gerencie o Mapper.
 */
import org.springframework.stereotype.Component;

/**
 * Mapper responsável pela conversão de eventos.
 */
@Component
public class EventPersistenceMapper {

    /*
     * ============================================================================
     * MÉTODO: toJpaEntity()
     * ============================================================================
     *
     * Converte a entidade de domínio para a entidade JPA.
     */
    public EventJpaEntity toJpaEntity(
        Event event) {

        return new EventJpaEntity(
            event.getId(),
            event.getName(),
            event.getDescription(),
            event.getEventDate(),
            event.isActive(),
            event.getCreatedAt(),
            event.getUpdatedAt()
        );
    }

    /*
     * ============================================================================
     * MÉTODO: toDomain()
     * ============================================================================
     *
     * Converte a entidade JPA para a entidade de domínio.
     */
    public Event toDomain(
        EventJpaEntity entity) {

        return new Event(
            entity.getId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getName(),
            entity.getDescription(),
            entity.getEventDate(),
            entity.isActive()
        );
    }
}
