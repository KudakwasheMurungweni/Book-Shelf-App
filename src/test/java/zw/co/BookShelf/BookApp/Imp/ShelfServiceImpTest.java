package zw.co.BookShelf.BookApp.Imp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import zw.co.BookShelf.BookApp.imp.ShelfServiceImpl;
import zw.co.BookShelf.BookApp.Repository.ShelfRepository;
import zw.co.BookShelf.BookApp.Repository.UserRepository;
import zw.co.BookShelf.BookApp.Mapper.ShelfMapper;
import zw.co.BookShelf.BookApp.dto.ShelfDto.CreateShelfDto;
import zw.co.BookShelf.BookApp.dto.ShelfDto.ShelfResponseDto;
import zw.co.BookShelf.BookApp.entity.Shelf;
import zw.co.BookShelf.BookApp.entity.User;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ShelfServiceImpTest {

    @Mock
    private ShelfRepository shelfRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ShelfMapper shelfMapper;

    @InjectMocks
    private ShelfServiceImpl shelfServiceImpl;

    public ShelfServiceImpTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateShelf_success() {
        Long userId = 1L;
        CreateShelfDto dto = new CreateShelfDto();
        User user = new User(); user.setId(userId);
        Shelf shelf = new Shelf();
        Shelf savedShelf = new Shelf();
        ShelfResponseDto responseDto = new ShelfResponseDto();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(shelfMapper.toEntity(dto)).thenReturn(shelf);
        when(shelfRepository.save(shelf)).thenReturn(savedShelf);
        when(shelfMapper.toResponseDto(savedShelf)).thenReturn(responseDto);

        ShelfResponseDto result = shelfServiceImpl.createShelf(dto, userId);
        assertThat(result).isEqualTo(responseDto);
    }

    @Test
    void testCreateShelf_userNotFound() {
        Long userId = 1L;
        CreateShelfDto dto = new CreateShelfDto();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> shelfServiceImpl.createShelf(dto, userId))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User not found");
    }

    @Test
    void testGetUserShelves() {
        Long userId = 1L;
        Shelf shelf = new Shelf();
        ShelfResponseDto responseDto = new ShelfResponseDto();
        when(shelfRepository.findByUserId(userId)).thenReturn(Collections.singletonList(shelf));
        when(shelfMapper.toResponseDto(shelf)).thenReturn(responseDto);

        assertThat(shelfServiceImpl.getUserShelves(userId)).containsExactly(responseDto);
    }

    @Test
    void testGetAllPublicShelves() {
        Shelf shelf = mock(Shelf.class);
        ShelfResponseDto responseDto = new ShelfResponseDto();
        when(shelfRepository.findAll()).thenReturn(Collections.singletonList(shelf));
        when(shelfMapper.toResponseDto(shelf)).thenReturn(responseDto);
        when(shelf.isPublic()).thenReturn(true);

        assertThat(shelfServiceImpl.getAllPublicShelves()).containsExactly(responseDto);
    }
}
