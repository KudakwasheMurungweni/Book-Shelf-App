package zw.co.BookShelf.BookApp.Service;

import zw.co.BookShelf.BookApp.entity.UserBook;
import java.util.List;
import java.util.Optional;

public interface UserBookService {
    List<UserBook> getAllUserBooks();
    Optional<UserBook> getUserBookById(Long id);
    List<UserBook> getUserBooksByUserId(Long userId);
    List<UserBook> getUserBooksByBookId(Long bookId);
    Optional<UserBook> getUserBookByUserIdAndBookId(Long userId, Long bookId);
    List<UserBook> getUserBooksByUserIdAndStatus(Long userId, String status);
    List<UserBook> getUserBooksOrderByDateDesc(Long userId);
    List<UserBook> getRecentlyAddedUserBooks(Long userId);
    boolean existsByUserIdAndBookId(Long userId, Long bookId);
    int countUserBooksByStatus(Long userId, String status);
    UserBook saveUserBook(UserBook userBook);
    void deleteUserBook(UserBook userBook);
    void deleteUserBookById(Long id);
}