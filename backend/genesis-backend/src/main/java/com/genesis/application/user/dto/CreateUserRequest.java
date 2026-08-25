package com.genesis.application.user.dto;

import com.genesis.domain.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(

    @NotBlank(message = "User name is required.")
    @Size(max = 120, message = "User name must have at most 120 characters.")
    String name,

    @NotBlank(message = "User email is required.")
    @Email(message = "User email must be valid.")
    @Size(max = 180, message = "User email must have at most 180 characters.")
    String email,

    @NotNull(message = "User role is required.")
    UserRole role

) {
}
