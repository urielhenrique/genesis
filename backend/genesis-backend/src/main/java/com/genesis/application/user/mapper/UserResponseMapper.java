package com.genesis.application.user.mapper;

import com.genesis.application.user.dto.UserResponse;
import com.genesis.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResponseMapper {

    public UserResponse toResponse(User user) {

        return new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole(),
            user.isActive(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }

    public List<UserResponse> toResponseList(
        List<User> users) {

        return users
            .stream()
            .map(this::toResponse)
            .toList();
    }
}
