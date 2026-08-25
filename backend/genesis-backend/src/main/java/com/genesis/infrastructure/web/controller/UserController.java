package com.genesis.infrastructure.web.controller;

import com.genesis.application.user.dto.CreateUserRequest;
import com.genesis.application.user.dto.UserResponse;
import com.genesis.application.user.mapper.UserResponseMapper;
import com.genesis.application.user.usecase.CreateUserUseCase;
import com.genesis.application.user.usecase.FindUserByIdUseCase;
import com.genesis.application.user.usecase.ListUsersUseCase;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    private final ListUsersUseCase listUsersUseCase;

    private final FindUserByIdUseCase findUserByIdUseCase;

    private final UserResponseMapper responseMapper;

    public UserController(
        CreateUserUseCase createUserUseCase,
        ListUsersUseCase listUsersUseCase,
        FindUserByIdUseCase findUserByIdUseCase,
        UserResponseMapper responseMapper) {

        this.createUserUseCase = createUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
        this.findUserByIdUseCase = findUserByIdUseCase;
        this.responseMapper = responseMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(
        @Valid @RequestBody CreateUserRequest request) {

        return responseMapper.toResponse(
            createUserUseCase.execute(
                request.name(),
                request.email(),
                request.role()
            )
        );
    }

    @GetMapping
    public List<UserResponse> findAll() {

        return responseMapper.toResponseList(
            listUsersUseCase.execute()
        );
    }

    @GetMapping("/{id}")
    public UserResponse findById(
        @PathVariable UUID id) {

        return responseMapper.toResponse(
            findUserByIdUseCase.execute(id)
        );
    }
}
