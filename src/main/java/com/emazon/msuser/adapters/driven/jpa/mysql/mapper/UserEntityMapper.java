package com.emazon.msuser.adapters.driven.jpa.mysql.mapper;

import com.emazon.msuser.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.msuser.domain.model.User;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity toUserEntity(User user);
    User toModel(UserEntity userEntity);
    default Optional<User> toUserOptional(Optional<UserEntity> userEntityOptional) {
        return userEntityOptional.map(this::toModel);
    }
}
