package com.hogar360.users.users.infrastructure.mappers;

import com.hogar360.users.users.domain.model.UserModel;
import com.hogar360.users.users.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserModel entityToModel(UserEntity userEntity);
    UserEntity modelToEntity(UserModel userModel);
}
