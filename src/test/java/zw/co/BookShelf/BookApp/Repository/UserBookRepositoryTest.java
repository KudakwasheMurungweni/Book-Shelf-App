package zw.co.BookShelf.BookApp.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import zw.co.BookShelf.BookApp.entity.User;
import zw.co.BookShelf.BookApp.entity.Book;
import zw.co.BookShelf.BookApp.entity.Shelf;
import zw.co.BookShelf.BookApp.entity.UserBook;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserBookRepositoryTest {

    @Autowired
    private UserBookRepository userBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    @Test
    void testFindByUserIdAndBookIdAndShelfId() {
        // ...setup user, book, shelf...
        User user = new User();
        user.setUserName("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password");
        userRepository.save(user);

        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Author");
        book.setGenre("Genre");
        bookRepository.save(book);

        Shelf shelf = new Shelf();
        shelf.setName("Test Shelf");
        shelf.setUser(user);
        shelfRepository.save(shelf);

        UserBook userBook = new UserBook();
        userBook.setUser(user);
        userBook.setBook(book);
        userBook.setShelf(shelf);
        userBookRepository.save(userBook);

        // Test findByUserId
        List<UserBook> byUser = userBookRepository.findByUserId(user.getId());
        assertThat(byUser).hasSize(1);

        // Test findByBookId
        List<UserBook> byBook = userBookRepository.findByBookId(book.getId());
        assertThat(byBook).hasSize(1);

        // Test findByShelfId
        List<UserBook> byShelf = userBookRepository.findByShelfId(shelf.getId());
        assertThat(byShelf).hasSize(1);

        // Test existsByUserIdAndBookIdAndShelfId
        boolean exists = userBookRepository.existsByUserIdAndBookIdAndShelfId(user.getId(), book.getId(), shelf.getId());
        assertThat(exists).isTrue();

        // Test ordering by addedDate
        List<UserBook> recent = userBookRepository.findByUserIdOrderByAddedDateDesc(user.getId());
        assertThat(recent).isNotEmpty();

        List<UserBook> top5 = userBookRepository.findTop5ByUserIdOrderByAddedDateDesc(user.getId());
        assertThat(top5).isNotEmpty();
    }
}
