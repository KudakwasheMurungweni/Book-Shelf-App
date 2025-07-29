package zw.co.BookShelf.BookApp.Repository;




import zw.co.BookShelf.BookApp.entity.Book;
import zw.co.BookShelf.BookApp.Repository.BookRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book book1, book2, book3;

    @BeforeEach
    void setUp() {
        book1 = new Book();
        book1.setTitle("Spring Boot in Action");
        book1.setAuthor("Craig Walls");
        book1.setGenre("Tech");
        book1.setNumberOfPages(320);
        book1.setPublicationYear(2019);

        book2 = new Book();
        book2.setTitle("Clean Code");
        book2.setAuthor("Robert C. Martin");
        book2.setGenre("Tech");
        book2.setNumberOfPages(464);
        book2.setPublicationYear(2008);

        book3 = new Book();
        book3.setTitle("Harry Potter");
        book3.setAuthor("J.K. Rowling");
        book3.setGenre("Fantasy");
        book3.setNumberOfPages(500);
        book3.setPublicationYear(2000);

        bookRepository.saveAll(List.of(book1, book2, book3));
    }

    @Test
    void testFindByTitleContainingIgnoreCase() {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase("spring");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).containsIgnoringCase("spring");
    }

    @Test
    void testFindByTitleContainingIgnoreCaseWithPagination() {
        Pageable pageable = PageRequest.of(0, 1);
        var page = bookRepository.findByTitleContainingIgnoreCase("code", pageable);

        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getContent().get(0).getTitle()).contains("Code");
    }

    @Test
    void testFindByAuthorContainingIgnoreCase() {
        List<Book> books = bookRepository.findByAuthorContainingIgnoreCase("martin");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).containsIgnoringCase("martin");
    }

    @Test
    void testFindByGenre() {
        List<Book> techBooks = bookRepository.findByGenre("Tech");
        assertThat(techBooks).hasSize(2);
    }

    @Test
    void testFindTop10ByOrderByBookIdDesc() {
        List<Book> recentBooks = bookRepository.findTop10ByOrderByBookIdDesc();
        assertThat(recentBooks).hasSize(3);
        assertThat(recentBooks.get(0).getBookId()).isGreaterThan(recentBooks.get(1).getBookId());
    }

    @Test
    void testFindByNumberOfPagesGreaterThan() {
        List<Book> thickBooks = bookRepository.findByNumberOfPagesGreaterThan(400);
        assertThat(thickBooks).hasSize(2);
    }

    @Test
    void testFindByNumberOfPagesBetween() {
        List<Book> midRangeBooks = bookRepository.findByNumberOfPagesBetween(300, 400);
        assertThat(midRangeBooks).hasSize(1);
        assertThat(midRangeBooks.get(0).getTitle()).isEqualTo("Spring Boot in Action");
    }

    @Test
    void testFindByTitle() {
        Optional<Book> result = bookRepository.findByTitle("Clean Code");
        assertThat(result).isPresent();
        assertThat(result.get().getAuthor()).isEqualTo("Robert C. Martin");
    }

    @Test
    void testFindByPublicationYearGreaterThanEqual() {
        List<Book> booksAfter2010 = bookRepository.findByPublicationYearGreaterThanEqual(2010);
        assertThat(booksAfter2010).hasSize(1);
        assertThat(booksAfter2010.get(0).getTitle()).isEqualTo("Spring Boot in Action");
    }
}
