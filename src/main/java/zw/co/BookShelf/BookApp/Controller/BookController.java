package zw.co.BookShelf.BookApp.Controller;

import zw.co.BookShelf.BookApp.entity.Book;
import zw.co.BookShelf.BookApp.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/title/{title}")
    public Optional<Book> getBookByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }

    @GetMapping("/search/title/{title}")
    public List<Book> getBooksByTitleContaining(@PathVariable String title) {
        return bookService.getBooksByTitleContaining(title);
    }

    @GetMapping("/search/author/{author}")
    public List<Book> getBooksByAuthorContaining(@PathVariable String author) {
        return bookService.getBooksByAuthorContaining(author);
    }

    @GetMapping("/genre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable String genre) {
        return bookService.getBooksByGenre(genre);
    }

    @GetMapping("/published")
    public List<Book> getPublishedBooks() {
        return bookService.getPublishedBooks();
    }

    @GetMapping("/latest")
    public List<Book> getLatestBooks() {
        return bookService.getLatestBooks();
    }

    @GetMapping("/pages/greater/{pages}")
    public List<Book> getBooksByPagesGreaterThan(@PathVariable int pages) {
        return bookService.getBooksByPagesGreaterThan(pages);
    }

    @GetMapping("/pages/between/{min}/{max}")
    public List<Book> getBooksByPagesBetween(@PathVariable int min, @PathVariable int max) {
        return bookService.getBooksByPagesBetween(min, max);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

    @DeleteMapping
    public void deleteBook(@RequestBody Book book) {
        bookService.deleteBook(book);
    }

    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable Long id) {
        return bookService.existsById(id);
    }
}