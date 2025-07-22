package zw.co.BookShelf.BookApp.Controller;

import jakarta.validation.Valid;
import zw.co.BookShelf.BookApp.dto.BookDto.BookCreateDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookResponseDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookSummaryDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookUpdateDto;
import zw.co.BookShelf.BookApp.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<BookSummaryDto>> getAllBooks() {
        List<BookSummaryDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        Optional<BookResponseDto> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<BookResponseDto> getBookByTitle(@PathVariable String title) {
        Optional<BookResponseDto> book = bookService.getBookByTitle(title);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/title/{title}")
    public ResponseEntity<List<BookSummaryDto>> getBooksByTitleContaining(@PathVariable String title) {
        List<BookSummaryDto> books = bookService.getBooksByTitleContaining(title);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/author/{author}")
    public ResponseEntity<List<BookSummaryDto>> getBooksByAuthorContaining(@PathVariable String author) {
        List<BookSummaryDto> books = bookService.getBooksByAuthorContaining(author);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookSummaryDto>> getBooksByGenre(@PathVariable String genre) {
        List<BookSummaryDto> books = bookService.getBooksByGenre(genre);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<BookSummaryDto>> getRecentBooks() {
        List<BookSummaryDto> books = bookService.getRecentBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/pages/greater/{pages}")
    public ResponseEntity<List<BookSummaryDto>> getBooksByPagesGreaterThan(@PathVariable int pages) {
        List<BookSummaryDto> books = bookService.getBooksByPagesGreaterThan(pages);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/pages/between/{min}/{max}")
    public ResponseEntity<List<BookSummaryDto>> getBooksByPagesBetween(@PathVariable int min, @PathVariable int max) {
        List<BookSummaryDto> books = bookService.getBooksByPagesBetween(min, max);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<BookSummaryDto>> getBooksByPublicationYear(@PathVariable int year) {
        List<BookSummaryDto> books = bookService.getBooksByPublicationYear(year);
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookCreateDto bookCreateDto) {
        BookResponseDto createdBook = bookService.createBook(bookCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @Valid @RequestBody BookUpdateDto bookUpdateDto) {
        bookUpdateDto.setBookId(id);
        try {
            BookResponseDto updatedBook = bookService.updateBook(bookUpdateDto);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBookById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        boolean exists = bookService.existsById(id);
        return ResponseEntity.ok(exists);
    }
}