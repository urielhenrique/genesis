/**
 * ============================================================================
 * CLASSE: Event
 * ============================================================================
 *
 * CAMADA:
 * Domain -> Event
 *
 * RESPONSABILIDADE:
 *
 * Representar um evento do sistema Genesis.
 *
 * ============================================================================
 */
package com.genesis.domain.event;

import com.genesis.domain.shared.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade de domínio responsável por representar um evento.
 */
public class Event extends BaseEntity {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Nome do evento.
     */
    private String name;

    /*
     * Descrição opcional do evento.
     */
    private String description;

    /*
     * Data e hora em que o evento acontecerá.
     */
    private LocalDateTime eventDate;

    /*
     * Indica se o evento está ativo.
     */
    private boolean active;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Utilizado para criar um novo evento.
     */
    public Event(
        String name,
        String description,
        LocalDateTime eventDate) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "Event name is required."
            );
        }

        if (eventDate == null) {
            throw new IllegalArgumentException(
                "Event date is required."
            );
        }

        this.name = name.trim();
        this.description = description;
        this.eventDate = eventDate;
        this.active = true;
    }

    /*
     * ============================================================================
     * CONSTRUTOR DE PERSISTÊNCIA
     * ============================================================================
     */
    public Event(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String name,
        String description,
        LocalDateTime eventDate,
        boolean active) {

        super(id, createdAt, updatedAt);

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "Event name is required."
            );
        }

        if (eventDate == null) {
            throw new IllegalArgumentException(
                "Event date is required."
            );
        }

        this.name = name;
        this.description = description;
        this.eventDate = eventDate;
        this.active = active;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     */

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public boolean isActive() {
        return active;
    }

    /*
     * ============================================================================
     * MÉTODO: update()
     * ============================================================================
     *
     * Atualiza os dados do evento.
     */
    public void update(
        String name,
        String description,
        LocalDateTime eventDate) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "Event name is required."
            );
        }

        if (eventDate == null) {
            throw new IllegalArgumentException(
                "Event date is required."
            );
        }

        this.name = name.trim();
        this.description = description;
        this.eventDate = eventDate;

        touch();
    }

    /*
     * ============================================================================
     * MÉTODO: activate()
     * ============================================================================
     */
    public void activate() {

        this.active = true;

        touch();
    }

    /*
     * ============================================================================
     * MÉTODO: deactivate()
     * ============================================================================
     */
    public void deactivate() {

        this.active = false;

        touch();
    }
}
