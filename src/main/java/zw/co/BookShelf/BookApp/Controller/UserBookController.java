package zw.co.BookShelf.BookApp.Controller;

import jakarta.validation.Valid;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookCreateDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookResponseDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookSummaryDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookUpdateDto;
import zw.co.BookShelf.BookApp.Service.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import zw.co.BookShelf.BookApp.dto.UserBookDto.AssignBookToShelfDTO;
import zw.co.BookShelf.BookApp.entity.Book;

@RestController
@RequestMapping("/api/userbooks")
public class UserBookController {

    private final UserBookService userBookService;

    @Autowired
    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @GetMapping
    public ResponseEntity<List<UserBookSummaryDto>> getAllUserBooks() {
        List<UserBookSummaryDto> userBooks = userBookService.getAllUserBooks();
        return ResponseEntity.ok(userBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBookResponseDto> getUserBookById(@PathVariable Long id) {
        Optional<UserBookResponseDto> userBook = userBookService.getUserBookById(id);
        return userBook.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserBookSummaryDto>> getUserBooksByUserId(@PathVariable Long userId) {
        List<UserBookSummaryDto> userBooks = userBookService.getUserBooksByUserId(userId);
        return ResponseEntity.ok(userBooks);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<UserBookSummaryDto>> getUserBooksByBookId(@PathVariable Long bookId) {
        List<UserBookSummaryDto> userBooks = userBookService.getUserBooksByBookId(bookId);
        return ResponseEntity.ok(userBooks);
    }

    @GetMapping("/user/{userId}/book/{bookId}")
    public ResponseEntity<UserBookResponseDto> getUserBookByUserIdAndBookId(@PathVariable Long userId, @PathVariable Long bookId) {
        Optional<UserBookResponseDto> userBook = userBookService.getUserBookByUserIdAndBookId(userId, bookId);
        return userBook.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<UserBookSummaryDto>> getUserBooksByUserIdAndStatus(@PathVariable Long userId, @PathVariable String status) {
        List<UserBookSummaryDto> userBooks = userBookService.getUserBooksByUserIdAndStatus(userId, status);
        return ResponseEntity.ok(userBooks);
    }

    @GetMapping("/user/{userId}/ordered")
    public ResponseEntity<List<UserBookSummaryDto>> getUserBooksOrderByDateDesc(@PathVariable Long userId) {
        List<UserBookSummaryDto> userBooks = userBookService.getUserBooksOrderByDateDesc(userId);
        return ResponseEntity.ok(userBooks);
    }

    @GetMapping("/user/{userId}/recent")
    public ResponseEntity<List<UserBookSummaryDto>> getRecentlyAddedUserBooks(@PathVariable Long userId) {
        List<UserBookSummaryDto> userBooks = userBookService.getRecentlyAddedUserBooks(userId);
        return ResponseEntity.ok(userBooks);
    }

    @GetMapping("/exists/user/{userId}/book/{bookId}")
    public ResponseEntity<Boolean> existsByUserIdAndBookId(@PathVariable Long userId, @PathVariable Long bookId) {
        boolean exists = userBookService.existsByUserIdAndBookId(userId, bookId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/count/user/{userId}/status/{status}")
    public ResponseEntity<Integer> countUserBooksByStatus(@PathVariable Long userId, @PathVariable String status) {
        int count = userBookService.countUserBooksByStatus(userId, status);
        return ResponseEntity.ok(count);
    }

    @PostMapping
    public ResponseEntity<UserBookResponseDto> createUserBook(@Valid @RequestBody UserBookCreateDto userBookCreateDto) {
        try {
            UserBookResponseDto createdUserBook = userBookService.createUserBook(userBookCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUserBook);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserBookResponseDto> updateUserBook(@PathVariable Long id, @Valid @RequestBody UserBookUpdateDto userBookUpdateDto) {
        userBookUpdateDto.setId(id);
        try {
            UserBookResponseDto updatedUserBook = userBookService.updateUserBook(userBookUpdateDto);
            return ResponseEntity.ok(updatedUserBook);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserBook(@PathVariable Long id) {
        try {
            userBookService.deleteUserBookById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // For demonstration, using a mock userId. Replace with authenticated user in production.
    private final Long mockUserId = 1L;

    @PostMapping(value = "/assign", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> assignBookToShelf(@RequestBody AssignBookToShelfDTO dto) {
        userBookService.assignBookToShelf(mockUserId, dto);
        return ResponseEntity.ok("Book assigned to shelf successfully");
    }

    @GetMapping("/shelf/{shelfId}")
    public ResponseEntity<List<Book>> getBooksByShelf(@PathVariable Long shelfId) {
        return ResponseEntity.ok(userBookService.getBooksByShelf(shelfId));
    }
}