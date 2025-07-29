package zw.co.BookShelf.BookApp.Security;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import zw.co.BookShelf.BookApp.Repository.UserRepository;
import zw.co.BookShelf.BookApp.entity.User;
import zw.co.BookShelf.BookApp.security.CustomUserDetailsService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class CustomUserDetailsServiceTest {

    @Test
    void testLoadUserByUsername_found() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = new User();
        user.setUserName("testuser");
        Mockito.when(userRepository.findByUserName("testuser")).thenReturn(Optional.of(user));

        CustomUserDetailsService service = new CustomUserDetailsService(userRepository);
        assertThat(service.loadUserByUsername("testuser")).isEqualTo(user);
    }

    @Test
    void testLoadUserByUsername_notFound() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findByUserName("missing")).thenReturn(Optional.empty());

        CustomUserDetailsService service = new CustomUserDetailsService(userRepository);
        assertThatThrownBy(() -> service.loadUserByUsername("missing"))
                .isInstanceOf(UsernameNotFoundException.class);
    }

    @Test
    void testLoadUserByUsername_emptyUsername() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        CustomUserDetailsService service = new CustomUserDetailsService(userRepository);
        assertThatThrownBy(() -> service.loadUserByUsername(""))
                .isInstanceOf(UsernameNotFoundException.class);
    }
}
