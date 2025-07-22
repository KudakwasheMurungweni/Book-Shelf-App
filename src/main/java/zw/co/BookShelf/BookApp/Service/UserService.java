package zw.co.BookShelf.BookApp.Service;

import zw.co.BookShelf.BookApp.dto.UserDto.UserCreateDto;
import zw.co.BookShelf.BookApp.dto.UserDto.UserResponseDto;
import zw.co.BookShelf.BookApp.dto.UserDto.UserUpdateDto;
import zw.co.BookShelf.BookApp.entity.User;

import java.util.Optional;
import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    Optional<UserResponseDto> getUserById(Long id);

    Optional<UserResponseDto> getUserByEmail(String email);

    Optional<UserResponseDto> getUserByUsername(String userName);

    boolean existsByEmail(String email);

    boolean existsByUsername(String userName);

    List<UserResponseDto> getUserByRole(String role);

    UserResponseDto createUser(UserCreateDto userCreateDto);

    UserResponseDto updateUser(UserUpdateDto userUpdateDto);

    void deleteUser(Long id);

    void deleteUser(User user);
}