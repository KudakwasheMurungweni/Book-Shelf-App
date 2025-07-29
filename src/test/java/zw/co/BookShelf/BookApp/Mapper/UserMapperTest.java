package zw.co.BookShelf.BookApp.Mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import zw.co.BookShelf.BookApp.dto.UserDto.UserCreateDto;
import zw.co.BookShelf.BookApp.dto.UserDto.UserResponseDto;
import zw.co.BookShelf.BookApp.dto.UserDto.UserUpdateDto;
import zw.co.BookShelf.BookApp.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    void testToEntityFromCreateDto() {
        UserCreateDto dto = new UserCreateDto();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        User user = mapper.toEntity(dto);
        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("John");
        assertThat(user.getLastName()).isEqualTo("Doe");
    }

    @Test
    void testToResponseDto() {
        User user = new User();
        user.setFirstName("Jane");
        user.setLastName("Smith");
        UserResponseDto response = mapper.toResponseDto(user);
        assertThat(response).isNotNull();
        assertThat(response.getFullName()).isEqualTo("Jane Smith");
    }

    @Test
    void testUpdateEntityFromDto() {
        User user = new User();
        user.setFirstName("Old");
        user.setLastName("Name");
        UserUpdateDto dto = new UserUpdateDto();
        dto.setFirstName("New");
        dto.setLastName("Name");
        mapper.updateEntityFromDto(dto, user);
        assertThat(user.getFirstName()).isEqualTo("New");
    }
}
