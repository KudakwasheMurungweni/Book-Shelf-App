package zw.co.BookShelf.BookApp.security;

import zw.co.BookShelf.BookApp.entity.User;
import zw.co.BookShelf.BookApp.Repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom implementation of UserDetailsService for Spring Security authentication.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user by username for authentication.
     * @param username the username identifying the user whose data is required.
     * @return UserDetails for authentication
     * @throws UsernameNotFoundException if the user could not be found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.trim().isEmpty()) {
            logger.warn("Attempted authentication with null or empty username");
            throw new UsernameNotFoundException("Username cannot be empty");
        }
        logger.info("Authenticating user: {}", username);
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> {
                    logger.warn("User not found: {}", username);
                    return new UsernameNotFoundException("User not found");
                });
        logger.info("User authenticated: {}", username);
        return user;
    }
}
