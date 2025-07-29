package zw.co.BookShelf.BookApp.Imp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import zw.co.BookShelf.BookApp.imp.UserServiceImp;
import zw.co.BookShelf.BookApp.Repository.UserRepository;
import zw.co.BookShelf.BookApp.Mapper.UserMapper;
import zw.co.BookShelf.BookApp.dto.UserDto.UserCreateDto;
import zw.co.BookShelf.BookApp.dto.UserDto.UserResponseDto;
import zw.co.BookShelf.BookApp.dto.UserDto.UserUpdateDto;
import zw.co.BookShelf.BookApp.entity.User;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserServiceImpTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImp userServiceImp;

    public UserServiceImpTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user = new User();
        UserResponseDto dto = new UserResponseDto();
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        when(userMapper.toResponseDto(user)).thenReturn(dto);

        assertThat(userServiceImp.getAllUsers()).containsExactly(dto);
    }

    @Test
    void testCreateUser() {
        UserCreateDto createDto = new UserCreateDto();
        User user = new User();
        User savedUser = new User();
        UserResponseDto responseDto = new UserResponseDto();

        when(userMapper.toEntity(createDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.toResponseDto(savedUser)).thenReturn(responseDto);

        assertThat(userServiceImp.createUser(createDto)).isEqualTo(responseDto);
    }

    @Test
    void testUpdateUser_success() {
        UserUpdateDto updateDto = new UserUpdateDto();
        updateDto.setId(1L);
        User existingUser = new User();
        User updatedUser = new User();
        UserResponseDto responseDto = new UserResponseDto();

        when(userRepository.findById(updateDto.getId())).thenReturn(Optional.of(existingUser));
        doNothing().when(userMapper).updateEntityFromDto(updateDto, existingUser);
        when(userRepository.save(existingUser)).thenReturn(updatedUser);
        when(userMapper.toResponseDto(updatedUser)).thenReturn(responseDto);

        assertThat(userServiceImp.updateUser(updateDto)).isEqualTo(responseDto);
    }

    @Test
    void testUpdateUser_notFound() {
        UserUpdateDto updateDto = new UserUpdateDto();
        updateDto.setId(1L);
        when(userRepository.findById(updateDto.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userServiceImp.updateUser(updateDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User not found with id");
    }
}
