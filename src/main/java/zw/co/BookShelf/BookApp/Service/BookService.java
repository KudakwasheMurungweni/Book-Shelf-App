package zw.co.BookShelf.BookApp.Service;

import zw.co.BookShelf.BookApp.dto.BookDto.BookCreateDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookResponseDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookSummaryDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookUpdateDto;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookSummaryDto> getAllBooks();
    Optional<BookResponseDto> getBookById(Long id);
    Optional<BookResponseDto> getBookByTitle(String title);
    List<BookSummaryDto> getBooksByTitleContaining(String title);
    List<BookSummaryDto> getBooksByAuthorContaining(String author);
    List<BookSummaryDto> getBooksByGenre(String genre);
    List<BookSummaryDto> getRecentBooks();
    List<BookSummaryDto> getBooksByPagesGreaterThan(int pages);
    List<BookSummaryDto> getBooksByPagesBetween(int min, int max);
    List<BookSummaryDto> getBooksByPublicationYear(int year);
    BookResponseDto createBook(BookCreateDto bookCreateDto);
    BookResponseDto updateBook(BookUpdateDto bookUpdateDto);
    void deleteBookById(Long id);
    boolean existsById(Long id);
}