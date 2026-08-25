package com.genesis.application.user.usecase;

import com.genesis.domain.exception.UserNotFoundException;
import com.genesis.domain.user.User;
import com.genesis.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindUserByIdUseCase {

    private final UserRepository repository;

    public FindUserByIdUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public User execute(UUID id) {

        return repository
            .findById(id)
            .orElseThrow(() ->
                new UserNotFoundException(id)
            );
    }
}
