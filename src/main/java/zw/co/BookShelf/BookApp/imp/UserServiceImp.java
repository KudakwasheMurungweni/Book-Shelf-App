package zw.co.BookShelf.BookApp.imp;

import zw.co.BookShelf.BookApp.entity.User;
import zw.co.BookShelf.BookApp.Service.UserService;
import org.springframework.transaction.annotation.Transactional;
import zw.co.BookShelf.BookApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImp implements UserService {
    private final UserRepository userrepository;

    @Autowired
    public UserServiceImp(UserRepository userrepository) {
        this.userrepository = userrepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userrepository.findAll();
    }

    @Override
    public List<User> getUserByRole(String role) {
        // Assuming you need to convert String to boolean or use a different method
        // You'll need to check your UserRepository interface for the correct method signature
        return userrepository.findByRole(Boolean.parseBoolean(role));
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userrepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userrepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByUsername(String userName) {
        return userrepository.findByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userrepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String userName) {
        return userrepository.existsByUserName(userName);
    }

    @Override
    public User saveuser(User user) {
        return userrepository.save(user);
    }

    @Override
    public void deleteuser(User user) {
        userrepository.delete(user);
    }
}