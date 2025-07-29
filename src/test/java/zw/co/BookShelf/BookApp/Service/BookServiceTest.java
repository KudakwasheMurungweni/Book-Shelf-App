package zw.co.BookShelf.BookApp.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zw.co.BookShelf.BookApp.dto.BookDto.BookResponseDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookSummaryDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookService bookService;

    @Test
    void testGetAllBooks() {
        when(bookService.getAllBooks()).thenReturn(Collections.emptyList());

        List<BookSummaryDto> books = bookService.getAllBooks();
        assertThat(books).isEmpty();
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void testGetBookById() {
        Long bookId = 1L;
        BookResponseDto response = new BookResponseDto();
        when(bookService.getBookById(bookId)).thenReturn(Optional.of(response));

        Optional<BookResponseDto> result = bookService.getBookById(bookId);
        assertThat(result).isPresent();
        verify(bookService, times(1)).getBookById(bookId);
    }
}
