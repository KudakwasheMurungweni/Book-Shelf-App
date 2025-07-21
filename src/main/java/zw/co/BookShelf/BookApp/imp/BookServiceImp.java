package zw.co.BookShelf.BookApp.imp;

import zw.co.BookShelf.BookApp.entity.Book;
import zw.co.BookShelf.BookApp.Service.BookService;
import zw.co.BookShelf.BookApp.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> getBooksByTitleContaining(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Book> getBooksByAuthorContaining(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    @Override
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    @Override
    public List<Book> getPublishedBooks() {
        return bookRepository.findByIsPublished(true);
    }

    @Override
    public List<Book> getLatestBooks() {
        return bookRepository.findTop10ByOrderByCreatedDateDesc();
    }

    @Override
    public List<Book> getBooksByPagesGreaterThan(int pages) {
        return bookRepository.findByPagesGreaterThan(pages);
    }

    @Override
    public List<Book> getBooksByPagesBetween(int min, int max) {
        return bookRepository.findByPagesBetween(min, max);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }
}