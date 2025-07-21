package zw.co.BookShelf.BookApp.Controller;

import zw.co.BookShelf.BookApp.entity.UserBook;
import zw.co.BookShelf.BookApp.Service.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userbooks")
public class UserBookController {

    private final UserBookService userBookService;

    @Autowired
    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @GetMapping
    public List<UserBook> getAllUserBooks() {
        return userBookService.getAllUserBooks();
    }

    @GetMapping("/{id}")
    public Optional<UserBook> getUserBookById(@PathVariable Long id) {
        return userBookService.getUserBookById(id);
    }

    @GetMapping("/user/{userId}")
    public List<UserBook> getUserBooksByUserId(@PathVariable Long userId) {
        return userBookService.getUserBooksByUserId(userId);
    }

    @GetMapping("/book/{bookId}")
    public List<UserBook> getUserBooksByBookId(@PathVariable Long bookId) {
        return userBookService.getUserBooksByBookId(bookId);
    }

    @GetMapping("/user/{userId}/book/{bookId}")
    public Optional<UserBook> getUserBookByUserIdAndBookId(@PathVariable Long userId, @PathVariable Long bookId) {
        return userBookService.getUserBookByUserIdAndBookId(userId, bookId);
    }

    @GetMapping("/user/{userId}/status/{status}")
    public List<UserBook> getUserBooksByUserIdAndStatus(@PathVariable Long userId, @PathVariable String status) {
        return userBookService.getUserBooksByUserIdAndStatus(userId, status);
    }

    @GetMapping("/user/{userId}/ordered")
    public List<UserBook> getUserBooksOrderByDateDesc(@PathVariable Long userId) {
        return userBookService.getUserBooksOrderByDateDesc(userId);
    }

    @GetMapping("/user/{userId}/recent")
    public List<UserBook> getRecentlyAddedUserBooks(@PathVariable Long userId) {
        return userBookService.getRecentlyAddedUserBooks(userId);
    }

    @GetMapping("/exists/user/{userId}/book/{bookId}")
    public boolean existsByUserIdAndBookId(@PathVariable Long userId, @PathVariable Long bookId) {
        return userBookService.existsByUserIdAndBookId(userId, bookId);
    }

    @GetMapping("/count/user/{userId}/status/{status}")
    public int countUserBooksByStatus(@PathVariable Long userId, @PathVariable String status) {
        return userBookService.countUserBooksByStatus(userId, status);
    }

    @PostMapping
    public UserBook createUserBook(@RequestBody UserBook userBook) {
        return userBookService.saveUserBook(userBook);
    }

    @PutMapping
    public UserBook updateUserBook(@RequestBody UserBook userBook) {
        return userBookService.saveUserBook(userBook);
    }

    @DeleteMapping("/{id}")
    public void deleteUserBookById(@PathVariable Long id) {
        userBookService.deleteUserBookById(id);
    }

    @DeleteMapping
    public void deleteUserBook(@RequestBody UserBook userBook) {
        userBookService.deleteUserBook(userBook);
    }
}