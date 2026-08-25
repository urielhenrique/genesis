package com.genesis.infrastructure.persistence.adapter;

import com.genesis.domain.user.User;
import com.genesis.domain.repository.UserRepository;
import com.genesis.infrastructure.persistence.entity.UserJpaEntity;
import com.genesis.infrastructure.persistence.mapper.UserPersistenceMapper;
import com.genesis.infrastructure.persistence.repository.UserJpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserPersistenceAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    private final UserPersistenceMapper mapper;

    public UserPersistenceAdapter(
        UserJpaRepository jpaRepository,
        UserPersistenceMapper mapper) {

        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {

        UserJpaEntity entity =
            mapper.toJpaEntity(user);

        UserJpaEntity savedEntity =
            jpaRepository.save(entity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(UUID id) {

        return jpaRepository
            .findByIdAndActiveTrue(id)
            .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {

        return jpaRepository
            .findByEmail(email)
            .map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {

        return jpaRepository
            .findAllByActiveTrue()
            .stream()
            .map(mapper::toDomain)
            .toList();
    }
}
