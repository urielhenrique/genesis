/**
 * ============================================================================
 * CLASSE: EventNotFoundException
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Exception
 *
 * RESPONSABILIDADE:
 *
 * Representar o erro quando um evento não é encontrado.
 *
 * ============================================================================
 */
package com.genesis.domain.exception;

import java.util.UUID;

/**
 * Exceção lançada quando um evento não existe.
 */
public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(UUID id) {

        super("Event not found: " + id);
    }
}
