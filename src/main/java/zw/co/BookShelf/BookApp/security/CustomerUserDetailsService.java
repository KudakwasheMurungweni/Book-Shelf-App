// src/main/java/zw/co/BookShelf/BookApp/security/CustomUserDetailsService.java
package zw.co.BookShelf.BookApp.security;

import zw.co.BookShelf.BookApp.entity.User;
import zw.co.BookShelf.BookApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
}