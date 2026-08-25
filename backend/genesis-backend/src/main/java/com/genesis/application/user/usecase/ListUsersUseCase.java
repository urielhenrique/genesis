package com.genesis.application.user.usecase;

import com.genesis.domain.repository.UserRepository;
import com.genesis.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListUsersUseCase {

    private final UserRepository repository;

    public ListUsersUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> execute() {

        return repository.findAll();
    }
}
