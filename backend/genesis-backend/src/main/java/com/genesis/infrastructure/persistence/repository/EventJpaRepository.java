/**
 * ============================================================================
 * INTERFACE: EventJpaRepository
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Repository
 *
 * RESPONSABILIDADE:
 *
 * Fornecer acesso à tabela event através do Spring Data JPA.
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
 * Entidade JPA do evento.
 */
import com.genesis.infrastructure.persistence.entity.EventJpaEntity;

/*
 * Spring Data JPA.
 */
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Identificador UUID.
 */
import java.util.UUID;

/**
 * Repository JPA responsável pela persistência de eventos.
 */
public interface EventJpaRepository
    extends JpaRepository<EventJpaEntity, UUID> {

}
