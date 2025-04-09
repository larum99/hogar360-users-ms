package com.hogar360.users.users.infraestructure.mappers;

import com.hogar360.users.users.domain.model.UserModel;
import com.hogar360.users.users.infraestructure.entities.UserEntity;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserModel entityToModel(UserEntity userEntity);
    UserEntity modelToEntity(UserModel userModel);

    default UserModel optionalEntityToModel(Optional<UserEntity> optionalEntity) {
        return optionalEntity.orElse(null) == null ? null : entityToModel(optionalEntity.get());
    }
}
