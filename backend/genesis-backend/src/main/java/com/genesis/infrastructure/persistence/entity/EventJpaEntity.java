/**
 * ============================================================================
 * CLASSE: EventJpaEntity
 * ============================================================================
 *
 * CAMADA:
 * Infrastructure -> Persistence -> Entity
 *
 * RESPONSABILIDADE:
 *
 * Representar a tabela event dentro da camada de persistência.
 *
 * Esta classe pertence ao JPA/Hibernate e não ao domínio.
 *
 * ============================================================================
 */
package com.genesis.infrastructure.persistence.entity;

/*
 * ============================================================================
 * IMPORTS
 * ============================================================================
 */

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade JPA correspondente à tabela event.
 */
@Entity
@Table(name = "event")
public class EventJpaEntity {

    /*
     * ============================================================================
     * ATRIBUTOS
     * ============================================================================
     */

    /*
     * Identificador único do evento.
     */
    @Id
    private UUID id;

    /*
     * Nome do evento.
     */
    @Column(nullable = false, length = 120)
    private String name;

    /*
     * Descrição do evento.
     */
    @Column(length = 500)
    private String description;

    /*
     * Data e hora do evento.
     */
    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    /*
     * Indica se o evento está ativo.
     */
    @Column(nullable = false)
    private boolean active;

    /*
     * Data de criação.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /*
     * Data da última atualização.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /*
     * ============================================================================
     * CONSTRUTOR JPA
     * ============================================================================
     *
     * Necessário para o Hibernate.
     */
    protected EventJpaEntity() {
    }

    /*
     * ============================================================================
     * CONSTRUTOR
     * ============================================================================
     *
     * Cria uma entidade JPA com todos os dados
     * necessários para persistência.
     */
    public EventJpaEntity(
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
