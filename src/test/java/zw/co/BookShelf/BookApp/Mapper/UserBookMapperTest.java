package zw.co.BookShelf.BookApp.Mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookCreateDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookSummaryDto;
import zw.co.BookShelf.BookApp.entity.UserBook;
import zw.co.BookShelf.BookApp.entity.Book;
import zw.co.BookShelf.BookApp.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

class UserBookMapperTest {

    private final UserBookMapper mapper = Mappers.getMapper(UserBookMapper.class);

    @Test
    void testToEntityFromCreateDto() {
        UserBookCreateDto dto = new UserBookCreateDto();
        dto.setUserId(1L);
        dto.setBookId(2L);
        UserBook userBook = mapper.toEntity(dto);
        assertThat(userBook).isNotNull();
    }

    @Test
    void testToSummaryDto() {
        UserBook userBook = new UserBook();
        Book book = new Book();
        book.setBookId(2L);
        book.setTitle("Title");
        book.setAuthor("Author");
        userBook.setBook(book);
        UserBookSummaryDto summary = mapper.toSummaryDto(userBook);
        assertThat(summary).isNotNull();
        assertThat(summary.getBookTitle()).isEqualTo("Title");
        assertThat(summary.getBookAuthor()).isEqualTo("Author");
    }
}
