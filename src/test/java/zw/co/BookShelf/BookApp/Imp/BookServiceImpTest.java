package zw.co.BookShelf.BookApp.Imp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import zw.co.BookShelf.BookApp.imp.BookServiceImp;
import zw.co.BookShelf.BookApp.Repository.BookRepository;
import zw.co.BookShelf.BookApp.Mapper.BookMapper;
import zw.co.BookShelf.BookApp.dto.BookDto.BookSummaryDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookResponseDto;
import zw.co.BookShelf.BookApp.entity.Book;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class BookServiceImpTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImp bookServiceImp;

    public BookServiceImpTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());
        when(bookMapper.toSummaryDto(any())).thenReturn(new BookSummaryDto());
        assertThat(bookServiceImp.getAllBooks()).isEmpty();
    }

    @Test
    void testGetBookById_found() {
        Book book = new Book();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        BookResponseDto dto = new BookResponseDto();
        when(bookMapper.toResponseDto(book)).thenReturn(dto);
        assertThat(bookServiceImp.getBookById(1L)).contains(dto);
    }

    @Test
    void testGetBookById_notFound() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());
        assertThat(bookServiceImp.getBookById(2L)).isEmpty();
    }

    @Test
    void testGetAllBooksPaged_noKeyword() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Book> page = new PageImpl<>(Collections.emptyList(), pageable, 0);
        when(bookRepository.findAll(pageable)).thenReturn(page);
        when(bookMapper.toSummaryDto(any())).thenReturn(new BookSummaryDto());
        Page<BookSummaryDto> result = bookServiceImp.getAllBooksPaged(pageable, null);
        assertThat(result.getContent()).isEmpty();
    }

    @Test
    void testGetAllBooksPaged_withKeyword() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Book> page = new PageImpl<>(Collections.emptyList(), pageable, 0);
        when(bookRepository.findByTitleContainingIgnoreCase("java", pageable)).thenReturn(page);
        when(bookMapper.toSummaryDto(any())).thenReturn(new BookSummaryDto());
        Page<BookSummaryDto> result = bookServiceImp.getAllBooksPaged(pageable, "java");
        assertThat(result.getContent()).isEmpty();
    }
}
