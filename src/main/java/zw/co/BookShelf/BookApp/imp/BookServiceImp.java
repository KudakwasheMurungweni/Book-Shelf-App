package zw.co.BookShelf.BookApp.imp;

import zw.co.BookShelf.BookApp.dto.BookDto.BookCreateDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookResponseDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookSummaryDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookUpdateDto;
import zw.co.BookShelf.BookApp.entity.Book;
import zw.co.BookShelf.BookApp.Service.BookService;
import zw.co.BookShelf.BookApp.Mapper.BookMapper;
import zw.co.BookShelf.BookApp.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImp(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookSummaryDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookResponseDto> getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toResponseDto);
    }

    @Override
    public Optional<BookResponseDto> getBookByTitle(String title) {
        return bookRepository.findByTitle(title)
                .map(bookMapper::toResponseDto);
    }

    @Override
    public List<BookSummaryDto> getBooksByTitleContaining(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(bookMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookSummaryDto> getBooksByAuthorContaining(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author)
                .stream()
                .map(bookMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookSummaryDto> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre)
                .stream()
                .map(bookMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookSummaryDto> getRecentBooks() {
        return bookRepository.findTop10ByOrderByBookIdDesc()
                .stream()
                .map(bookMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookSummaryDto> getBooksByPagesGreaterThan(int pages) {
        return bookRepository.findByNumberOfPagesGreaterThan(pages)
                .stream()
                .map(bookMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookSummaryDto> getBooksByPagesBetween(int min, int max) {
        return bookRepository.findByNumberOfPagesBetween(min, max)
                .stream()
                .map(bookMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookSummaryDto> getBooksByPublicationYear(int year) {
        return bookRepository.findByPublicationYearGreaterThanEqual(year)
                .stream()
                .map(bookMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDto createBook(BookCreateDto bookCreateDto) {
        Book book = bookMapper.toEntity(bookCreateDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toResponseDto(savedBook);
    }

    @Override
    public BookResponseDto updateBook(BookUpdateDto bookUpdateDto) {
        Book existingBook = bookRepository.findById(bookUpdateDto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookUpdateDto.getBookId()));

        bookMapper.updateEntityFromDto(bookUpdateDto, existingBook);
        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toResponseDto(updatedBook);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    @Override
    public Page<BookSummaryDto> getAllBooksPaged(Pageable pageable, String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return bookRepository.findAll(pageable)
                    .map(bookMapper::toSummaryDto);
        } else {
            return bookRepository.findByTitleContainingIgnoreCase(keyword, pageable)
                    .map(bookMapper::toSummaryDto);
        }
    }
}