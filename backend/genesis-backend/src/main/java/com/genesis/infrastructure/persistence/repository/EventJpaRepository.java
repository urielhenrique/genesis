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
import java.util.List;
import java.util.Optional;

/**
 * Repository JPA responsável pela persistência de eventos.
 */
public interface EventJpaRepository
    extends JpaRepository<EventJpaEntity, UUID> {

    /*
     * Busca somente eventos ativos pelo ID.
     */
    Optional<EventJpaEntity> findByIdAndActiveTrue(
        UUID id
    );

    /*
     * Lista somente eventos ativos.
     */
    List<EventJpaEntity> findAllByActiveTrue();
}
