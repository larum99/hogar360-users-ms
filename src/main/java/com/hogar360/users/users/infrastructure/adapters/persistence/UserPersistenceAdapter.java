package com.hogar360.users.users.infrastructure.adapters.persistence;

import com.hogar360.users.users.domain.model.UserModel;
import com.hogar360.users.users.domain.ports.out.UserPersistencePort;
import com.hogar360.users.users.infrastructure.entities.UserEntity;
import com.hogar360.users.users.infrastructure.mappers.UserEntityMapper;
import com.hogar360.users.users.infrastructure.repositories.mysql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public UserModel getUserByEmail(String email) {
        Optional<UserEntity> optionalEntity = userRepository.findByEmail(email);
        return userEntityMapper.optionalEntityToModel(optionalEntity);
    }
}
