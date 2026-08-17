/**
 * ============================================================================
 * INTERFACE: EventRepository
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Repository
 *
 * RESPONSABILIDADE:
 *
 * Definir o contrato de persistência dos eventos.
 *
 * O Domain conhece apenas esta interface.
 * A implementação ficará na Infrastructure.
 *
 * ============================================================================
 */
package com.genesis.domain.repository;

/*
 * Entidade de domínio do evento.
 */
import com.genesis.domain.event.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Contrato de persistência dos eventos.
 */
public interface EventRepository {

    /*
     * ============================================================================
     * MÉTODO: save()
     * ============================================================================
     *
     * Salva ou atualiza um evento.
     */
    Event save(Event event);

    /*
     * ============================================================================
     * MÉTODO: findById()
     * ============================================================================
     *
     * Busca um evento pelo ID.
     */
    Optional<Event> findById(UUID id);

    /*
     * ============================================================================
     * MÉTODO: findAll()
     * ============================================================================
     *
     * Retorna todos os eventos.
     */
    List<Event> findAll();

    /*
     * ============================================================================
     * MÉTODO: delete()
     * ============================================================================
     *
     * Remove um evento.
     */
    void delete(Event event);
}
