package zw.co.BookShelf.BookApp.imp;

import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookCreateDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookResponseDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookSummaryDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookUpdateDto;
import zw.co.BookShelf.BookApp.entity.UserBook;
import zw.co.BookShelf.BookApp.entity.User;
import zw.co.BookShelf.BookApp.entity.Book;
import zw.co.BookShelf.BookApp.Service.UserBookService;
import zw.co.BookShelf.BookApp.Mapper.UserBookMapper;
import zw.co.BookShelf.BookApp.Repository.UserBookRepository;
import zw.co.BookShelf.BookApp.Repository.UserRepository;
import zw.co.BookShelf.BookApp.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserBookServiceImp implements UserBookService {

    private final UserBookRepository userBookRepository;
    private final UserBookMapper userBookMapper;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public UserBookServiceImp(UserBookRepository userBookRepository, UserBookMapper userBookMapper,
                              UserRepository userRepository, BookRepository bookRepository) {
        this.userBookRepository = userBookRepository;
        this.userBookMapper = userBookMapper;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<UserBookSummaryDto> getAllUserBooks() {
        return userBookRepository.findAll()
                .stream()
                .map(userBookMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserBookResponseDto> getUserBookById(Long id) {
        return userBookRepository.findById(id)
                .map(userBookMapper::toResponseDto);
    }

    @Override
    public List<UserBookSummaryDto> getUserBooksByUserId(Long userId) {
        return userBookRepository.findByUserId(userId)
                .stream()
                .map(userBookMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserBookSummaryDto> getUserBooksByBookId(Long bookId) {
        return userBookRepository.findByBookId(bookId)
                .stream()
                .map(userBookMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserBookResponseDto> getUserBookByUserIdAndBookId(Long userId, Long bookId) {
        return userBookRepository.findByUserIdAndBookId(userId, bookId)
                .map(userBookMapper::toResponseDto);
    }

    @Override
    public List<UserBookSummaryDto> getUserBooksByUserIdAndStatus(Long userId, String status) {
        return userBookRepository.findByUserIdAndStatus(userId, status)
                .stream()
                .map(userBookMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserBookSummaryDto> getUserBooksOrderByDateDesc(Long userId) {
        return userBookRepository.findByUserIdOrderByAddedDateDesc(userId)
                .stream()
                .map(userBookMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserBookSummaryDto> getRecentlyAddedUserBooks(Long userId) {
        return userBookRepository.findTop5ByUserIdOrderByAddedDateDesc(userId)
                .stream()
                .map(userBookMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByUserIdAndBookId(Long userId, Long bookId) {
        return userBookRepository.existsByUserIdAndBookId(userId, bookId);
    }

    @Override
    public int countUserBooksByStatus(Long userId, String status) {
        return userBookRepository.countByUserIdAndStatus(userId, status);
    }

    @Override
    public UserBookResponseDto createUserBook(UserBookCreateDto userBookCreateDto) {
        // Verify user exists
        User user = userRepository.findById(userBookCreateDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userBookCreateDto.getUserId()));

        // Verify book exists
        Book book = bookRepository.findById(userBookCreateDto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + userBookCreateDto.getBookId()));

        // Check if user already has this book
        if (userBookRepository.existsByUserIdAndBookId(userBookCreateDto.getUserId(), userBookCreateDto.getBookId())) {
            throw new RuntimeException("User already has this book in their shelf");
        }

        UserBook userBook = userBookMapper.toEntity(userBookCreateDto);
        userBook.setUser(user);
        userBook.setBook(book);

        UserBook savedUserBook = userBookRepository.save(userBook);
        return userBookMapper.toResponseDto(savedUserBook);
    }

    @Override
    public UserBookResponseDto updateUserBook(UserBookUpdateDto userBookUpdateDto) {
        UserBook existingUserBook = userBookRepository.findById(userBookUpdateDto.getId())
                .orElseThrow(() -> new RuntimeException("UserBook not found with id: " + userBookUpdateDto.getId()));

        userBookMapper.updateEntityFromDto(userBookUpdateDto, existingUserBook);
        UserBook updatedUserBook = userBookRepository.save(existingUserBook);
        return userBookMapper.toResponseDto(updatedUserBook);
    }

    @Override
    public void deleteUserBookById(Long id) {
        userBookRepository.deleteById(id);
    }
}