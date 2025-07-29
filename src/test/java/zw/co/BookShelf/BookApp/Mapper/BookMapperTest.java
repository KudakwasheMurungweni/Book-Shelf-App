package zw.co.BookShelf.BookApp.Mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import zw.co.BookShelf.BookApp.dto.BookDto.BookCreateDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookResponseDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookUpdateDto;
import zw.co.BookShelf.BookApp.entity.Book;

import static org.assertj.core.api.Assertions.assertThat;

class BookMapperTest {

    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    @Test
    void testToEntityFromCreateDto() {
        BookCreateDto dto = new BookCreateDto();
        dto.setTitle("Book Title");
        Book book = mapper.toEntity(dto);
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("Book Title");
    }

    @Test
    void testToResponseDto() {
        Book book = new Book();
        book.setTitle("Book Title");
        BookResponseDto response = mapper.toResponseDto(book);
        assertThat(response).isNotNull();
        assertThat(response.getTitle()).isEqualTo("Book Title");
    }

    @Test
    void testUpdateEntityFromDto() {
        Book book = new Book();
        book.setTitle("Old Title");
        BookUpdateDto dto = new BookUpdateDto();
        dto.setTitle("New Title");
        mapper.updateEntityFromDto(dto, book);
        assertThat(book.getTitle()).isEqualTo("New Title");
    }
}
