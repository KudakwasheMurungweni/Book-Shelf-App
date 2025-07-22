package zw.co.BookShelf.BookApp.Repository;

import zw.co.BookShelf.BookApp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByGenre(String genre);

    @Query("SELECT b FROM Book b ORDER BY b.bookId DESC")
    List<Book> findTop10ByOrderByBookIdDesc();

    List<Book> findByNumberOfPagesGreaterThan(int pages);

    List<Book> findByNumberOfPagesBetween(int min, int max);

    Optional<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.publicationYear >= :year")
    List<Book> findByPublicationYearGreaterThanEqual(@Param("year") int year);
}