/**
 * ============================================================================
 * CLASSE: CreateEventRequest
 * ============================================================================
 *
 * CAMADA:
 * Application -> Event -> DTO
 *
 * RESPONSABILIDADE:
 *
 * Representar os dados enviados pelo cliente para
 * criação de um evento.
 *
 * ============================================================================
 */
package com.genesis.application.event.dto;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * DTO utilizado para criação de eventos.
 */
public class CreateEventRequest {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Nome do evento.
     */
    @NotBlank(message = "Event name is required.")
    @Size(max = 120)
    private final String name;

    /*
     * Descrição opcional.
     */
    @Size(max = 500)
    private final String description;

    /*
     * Data e hora do evento.
     */
    @NotNull(message = "Event date is required.")
    private final LocalDateTime eventDate;

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     */
    public CreateEventRequest(
        String name,
        String description,
        LocalDateTime eventDate) {

        this.name = name == null
            ? null
            : name.trim();

        this.description = description;
        this.eventDate = eventDate;
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
}
