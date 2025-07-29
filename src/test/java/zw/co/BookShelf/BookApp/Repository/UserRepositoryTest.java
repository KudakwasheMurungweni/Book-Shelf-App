// src/test/java/zw/co/BookShelf/BookApp/Repository/UserRepositoryTest.java
package zw.co.BookShelf.BookApp.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import zw.co.BookShelf.BookApp.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByUserName() {
        User user = new User();
        user.setUserName("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        userRepository.save(user);

        var found = userRepository.findByUserName("testuser");
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("test@example.com");
    }
}