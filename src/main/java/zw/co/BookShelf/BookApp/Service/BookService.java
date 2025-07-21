package zw.co.BookShelf.BookApp.Service;

import zw.co.BookShelf.BookApp.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    Optional<Book> getBookByTitle(String title);
    List<Book> getBooksByTitleContaining(String title);
    List<Book> getBooksByAuthorContaining(String author);
    List<Book> getBooksByGenre(String genre);
    List<Book> getPublishedBooks();
    List<Book> getLatestBooks();
    List<Book> getBooksByPagesGreaterThan(int pages);
    List<Book> getBooksByPagesBetween(int min, int max);
    Book saveBook(Book book);
    void deleteBook(Book book);
    void deleteBookById(Long id);
    boolean existsById(Long id);
}