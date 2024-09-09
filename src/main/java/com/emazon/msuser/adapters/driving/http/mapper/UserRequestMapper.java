package com.emazon.msuser.adapters.driving.http.mapper;

import com.emazon.msuser.adapters.driving.http.dto.request.UserRequest;
import com.emazon.msuser.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {
    @Mapping(target = "role.id", constant = "1L")
    @Mapping(target = "role.name", constant = "name")
    @Mapping(target = "role.description", constant = "description")
    User toUserModel(UserRequest userRequest);
}
