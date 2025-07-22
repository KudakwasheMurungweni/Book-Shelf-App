package zw.co.BookShelf.BookApp.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import zw.co.BookShelf.BookApp.dto.UserDto.UserCreateDto;
import zw.co.BookShelf.BookApp.dto.UserDto.UserResponseDto;
import zw.co.BookShelf.BookApp.dto.UserDto.UserUpdateDto;
import zw.co.BookShelf.BookApp.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserCreateDto userCreateDto);

    @Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    UserResponseDto toResponseDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "role", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UserUpdateDto userUpdateDto, @MappingTarget User user);

    User toEntity(UserUpdateDto userUpdateDto);
}