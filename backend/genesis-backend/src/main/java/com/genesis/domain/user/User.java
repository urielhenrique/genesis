package com.genesis.domain.user;

import com.genesis.domain.shared.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;


public class User extends BaseEntity {

    private String name;

    private String email;

    private UserRole role;

    private boolean active;

    public User(
        UUID id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String name,
        String email,
        UserRole role,
        boolean active) {

        super(id, createdAt, updatedAt);

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "User name is required."
            );
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                "User email is required."
            );
        }

        if (role == null) {
            throw new IllegalArgumentException(
                "User role is required."
            );
        }

        this.name = name;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    public User(String name, String email, UserRole role) {

        this(
            UUID.randomUUID(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            name,
            email,
            role,
            true
        );
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }

    public void update(
        String name,
        String email,
        UserRole role) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "User name is required."
            );
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                "User email is required."
            );
        }

        if (role == null) {
            throw new IllegalArgumentException(
                "User role is required."
            );
        }

        this.name = name.trim();
        this.email = email.trim().toLowerCase();
        this.role = role;

        touch();
    }

    public void activate() {

        this.active = true;

        touch();
    }

    public void deactivate() {

        this.active = false;

        touch();
    }
}
