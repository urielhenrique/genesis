package com.genesis.application.user.dto;

import com.genesis.domain.user.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(

    UUID id,

    String name,

    String email,

    UserRole role,

    boolean active,

    LocalDateTime createdAt,

    LocalDateTime updatedAt

) {
}
