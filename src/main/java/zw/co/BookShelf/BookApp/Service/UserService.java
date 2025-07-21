package zw.co.BookShelf.BookApp.Service;

import zw.co.BookShelf.BookApp.entity.User;

import java.util.Optional;

import java.util.List;



public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByUsername(String userName);
    boolean existsByEmail(String email);

boolean existsByUsername(String userName);

List<User> getUserByRole(String role);

User saveuser(User user);

void deleteuser(User user);

}