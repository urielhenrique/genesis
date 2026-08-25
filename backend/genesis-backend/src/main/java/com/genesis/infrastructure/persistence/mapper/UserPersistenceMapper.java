package com.genesis.infrastructure.persistence.mapper;

import com.genesis.domain.user.User;
import com.genesis.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {

    public UserJpaEntity toJpaEntity(User user) {

        return new UserJpaEntity(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole(),
            user.isActive(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }

    public User toDomain(UserJpaEntity entity) {

        return new User(
            entity.getId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getName(),
            entity.getEmail(),
            entity.getRole(),
            entity.isActive()
        );
    }
}
