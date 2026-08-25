package com.genesis.application.user.usecase;

import com.genesis.domain.user.User;
import com.genesis.domain.user.UserRole;
import com.genesis.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final UserRepository repository;

    public CreateUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public User execute(
        String name,
        String email,
        UserRole role) {

        repository
            .findByEmail(email)
            .ifPresent(existing -> {
                throw new IllegalArgumentException(
                    "User with email '" + email + "' already exists."
                );
            });

        User user = new User(
            name,
            email,
            role
        );

        return repository.save(user);
    }
}
