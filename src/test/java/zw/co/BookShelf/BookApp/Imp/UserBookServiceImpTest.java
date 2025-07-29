package zw.co.BookShelf.BookApp.Imp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import zw.co.BookShelf.BookApp.imp.UserBookServiceImp;
import zw.co.BookShelf.BookApp.Repository.UserBookRepository;
import zw.co.BookShelf.BookApp.Mapper.UserBookMapper;
import zw.co.BookShelf.BookApp.Repository.UserRepository;
import zw.co.BookShelf.BookApp.Repository.BookRepository;
import zw.co.BookShelf.BookApp.Repository.ShelfRepository;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookSummaryDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.AssignBookToShelfDTO;
import zw.co.BookShelf.BookApp.entity.UserBook;
import zw.co.BookShelf.BookApp.entity.User;
import zw.co.BookShelf.BookApp.entity.Book;
import zw.co.BookShelf.BookApp.entity.Shelf;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserBookServiceImpTest {

    @Mock
    private UserBookRepository userBookRepository;
    @Mock
    private UserBookMapper userBookMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private ShelfRepository shelfRepository;

    @InjectMocks
    private UserBookServiceImp userBookServiceImp;

    public UserBookServiceImpTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUserBooks() {
        when(userBookRepository.findAll()).thenReturn(Collections.emptyList());
        when(userBookMapper.toSummaryDto(any())).thenReturn(new UserBookSummaryDto());
        assertThat(userBookServiceImp.getAllUserBooks()).isEmpty();
    }

    @Test
    void testAssignBookToShelf_success() {
        Long userId = 1L;
        AssignBookToShelfDTO dto = new AssignBookToShelfDTO();
        dto.setBookId(2L);
        dto.setShelfId(3L);

        when(userBookRepository.existsByUserIdAndBookIdAndShelfId(userId, dto.getBookId(), dto.getShelfId())).thenReturn(false);
        User user = new User(); user.setId(userId);
        Book book = new Book(); book.setBookId(dto.getBookId());
        Shelf shelf = new Shelf(); shelf.setId(dto.getShelfId()); shelf.setUser(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(bookRepository.findById(dto.getBookId())).thenReturn(Optional.of(book));
        when(shelfRepository.findById(dto.getShelfId())).thenReturn(Optional.of(shelf));

        userBookServiceImp.assignBookToShelf(userId, dto);

        verify(userBookRepository, times(1)).save(any(UserBook.class));
    }

    @Test
    void testAssignBookToShelf_alreadyExists() {
        Long userId = 1L;
        AssignBookToShelfDTO dto = new AssignBookToShelfDTO();
        dto.setBookId(2L);
        dto.setShelfId(3L);

        when(userBookRepository.existsByUserIdAndBookIdAndShelfId(userId, dto.getBookId(), dto.getShelfId())).thenReturn(true);

        assertThatThrownBy(() -> userBookServiceImp.assignBookToShelf(userId, dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Book already exists on this shelf");
    }

    @Test
    void testGetBooksByShelf() {
        Long shelfId = 3L;
        UserBook userBook = new UserBook();
        Book book = new Book();
        userBook.setBook(book);
        when(userBookRepository.findByShelfId(shelfId)).thenReturn(Collections.singletonList(userBook));
        assertThat(userBookServiceImp.getBooksByShelf(shelfId)).containsExactly(book);
    }
}
