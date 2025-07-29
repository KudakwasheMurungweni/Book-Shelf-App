package zw.co.BookShelf.BookApp.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import zw.co.BookShelf.BookApp.entity.Shelf;
import zw.co.BookShelf.BookApp.entity.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ShelfRepositoryTest {

    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByUserId() {
        User user = new User();
        user.setUserName("shelfuser");
        user.setEmail("shelfuser@example.com");
        user.setPassword("password");
        userRepository.save(user);

        Shelf shelf1 = new Shelf();
        shelf1.setName("Shelf 1");
        shelf1.setUser(user);

        Shelf shelf2 = new Shelf();
        shelf2.setName("Shelf 2");
        shelf2.setUser(user);

        shelfRepository.saveAll(List.of(shelf1, shelf2));

        List<Shelf> shelves = shelfRepository.findByUserId(user.getId());
        assertThat(shelves).hasSize(2);
        assertThat(shelves).extracting(Shelf::getName).contains("Shelf 1", "Shelf 2");
    }
}
