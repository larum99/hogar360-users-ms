package com.hogar360.users.users.application.mappers;

import com.hogar360.users.users.application.dto.request.SaveUserRequest;
import com.hogar360.users.users.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {
    UserModel requestToModel(SaveUserRequest saveUserRequest);
}
