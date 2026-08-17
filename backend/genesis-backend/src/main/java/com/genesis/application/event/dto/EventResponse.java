/**
 * ============================================================================
 * CLASSE: EventResponse
 * ============================================================================
 *
 * CAMADA:
 * Application -> Event -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados de um evento que serão
 * retornados pela API.
 *
 * ============================================================================
 */
package com.genesis.application.event.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO utilizado como resposta da API.
 */
public class EventResponse {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    private final UUID id;

    private final String name;

    private final String description;

    private final LocalDateTime eventDate;

    private final boolean active;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */

    public EventResponse(
        UUID id,
        String name,
        String description,
        LocalDateTime eventDate,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.eventDate = eventDate;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /*
     * ============================================================================
     * GETTERS
     * ============================================================================
     */

    public UUID getId() {
        return id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
