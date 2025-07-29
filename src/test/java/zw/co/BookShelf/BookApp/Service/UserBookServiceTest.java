package zw.co.BookShelf.BookApp.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zw.co.BookShelf.BookApp.dto.UserBookDto.AssignBookToShelfDTO;
import zw.co.BookShelf.BookApp.entity.Book;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserBookServiceTest {

    @Mock
    private UserBookService userBookService;

    @Test
    void testAssignBookToShelf() {
        Long userId = 1L;
        AssignBookToShelfDTO dto = new AssignBookToShelfDTO();
        doNothing().when(userBookService).assignBookToShelf(userId, dto);

        userBookService.assignBookToShelf(userId, dto);
        verify(userBookService, times(1)).assignBookToShelf(userId, dto);
    }

    @Test
    void testGetBooksByShelf() {
        Long shelfId = 1L;
        when(userBookService.getBooksByShelf(shelfId)).thenReturn(Collections.emptyList());

        List<Book> books = userBookService.getBooksByShelf(shelfId);
        assert books.isEmpty();
        verify(userBookService, times(1)).getBooksByShelf(shelfId);
    }
}
