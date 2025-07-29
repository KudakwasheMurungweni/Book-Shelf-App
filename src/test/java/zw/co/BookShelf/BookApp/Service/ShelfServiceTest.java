package zw.co.BookShelf.BookApp.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zw.co.BookShelf.BookApp.dto.ShelfDto.CreateShelfDto;
import zw.co.BookShelf.BookApp.dto.ShelfDto.ShelfResponseDto;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShelfServiceTest {

    @Mock
    private ShelfService shelfService;

    @Test
    void testCreateShelf() {
        CreateShelfDto dto = new CreateShelfDto();
        Long userId = 1L;
        ShelfResponseDto response = new ShelfResponseDto();
        when(shelfService.createShelf(dto, userId)).thenReturn(response);

        ShelfResponseDto result = shelfService.createShelf(dto, userId);
        assertThat(result).isNotNull();
        verify(shelfService, times(1)).createShelf(dto, userId);
    }

    @Test
    void testGetUserShelves() {
        Long userId = 1L;
        when(shelfService.getUserShelves(userId)).thenReturn(Collections.emptyList());

        List<ShelfResponseDto> shelves = shelfService.getUserShelves(userId);
        assertThat(shelves).isEmpty();
        verify(shelfService, times(1)).getUserShelves(userId);
    }
}
