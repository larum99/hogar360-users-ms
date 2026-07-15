package com.hogar360.users.users.infrastructure.adapters.persistence;

import com.hogar360.users.users.domain.model.UserModel;
import com.hogar360.users.users.domain.ports.out.UserPersistencePort;
import com.hogar360.users.users.infrastructure.entities.UserEntity;
import com.hogar360.users.users.infrastructure.mappers.UserEntityMapper;
import com.hogar360.users.users.infrastructure.repositories.mysql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void saveUser(UserModel userModel) {
        UserEntity entity = userEntityMapper.modelToEntity(userModel);
        userRepository.save(entity);
    }

    @Override
    public Optional<UserModel> getUserByEmail(String email) {
        Optional<UserEntity> entityOpt = userRepository.findByEmail(email);
        if (entityOpt.isPresent()) {
            UserModel model = userEntityMapper.entityToModel(entityOpt.get());
            return Optional.of(model);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserModel> getUserByDocument(String identityDocument) {
        Optional<UserEntity> entityOpt = userRepository.findByIdentityDocument(identityDocument);
        if (entityOpt.isPresent()) {
            UserModel model = userEntityMapper.entityToModel(entityOpt.get());
            return Optional.of(model);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        Optional<UserEntity> entityOpt = userRepository.findById(id);
        if (entityOpt.isPresent()) {
            UserModel model = userEntityMapper.entityToModel(entityOpt.get());
            return Optional.of(model);
        } else {
            return Optional.empty();
        }
    }
}
