package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import org.mapstruct.Mapper;

// MapStruct will implement this interface at compile time
// Pass componentModel=spring as an argument, so that Spring can create beans of this type at runtime
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
