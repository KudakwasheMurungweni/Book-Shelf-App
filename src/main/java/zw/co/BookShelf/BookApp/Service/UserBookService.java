package zw.co.BookShelf.BookApp.Service;

import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookCreateDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookResponseDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookSummaryDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookUpdateDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.AssignBookToShelfDTO;
import zw.co.BookShelf.BookApp.entity.Book;
import java.util.List;
import java.util.Optional;

public interface UserBookService {
    List<UserBookSummaryDto> getAllUserBooks();
    Optional<UserBookResponseDto> getUserBookById(Long id);
    List<UserBookSummaryDto> getUserBooksByUserId(Long userId);
    List<UserBookSummaryDto> getUserBooksByBookId(Long bookId);
    Optional<UserBookResponseDto> getUserBookByUserIdAndBookId(Long userId, Long bookId);
    List<UserBookSummaryDto> getUserBooksByUserIdAndStatus(Long userId, String status);
    List<UserBookSummaryDto> getUserBooksOrderByDateDesc(Long userId);
    List<UserBookSummaryDto> getRecentlyAddedUserBooks(Long userId);
    boolean existsByUserIdAndBookId(Long userId, Long bookId);
    int countUserBooksByStatus(Long userId, String status);
    UserBookResponseDto createUserBook(UserBookCreateDto userBookCreateDto);
    UserBookResponseDto updateUserBook(UserBookUpdateDto userBookUpdateDto);
    void deleteUserBookById(Long id);
    void assignBookToShelf(Long userId, AssignBookToShelfDTO dto);
    List<Book> getBooksByShelf(Long shelfId);
}