package zw.co.BookShelf.BookApp.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import zw.co.BookShelf.BookApp.dto.BookDto.BookCreateDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookResponseDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookSummaryDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookUpdateDto;
import zw.co.BookShelf.BookApp.entity.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "bookId", ignore = true)
    Book toEntity(BookCreateDto bookCreateDto);

    BookResponseDto toResponseDto(Book book);

    BookSummaryDto toSummaryDto(Book book);

    @Mapping(target = "bookId", ignore = true)
    @Mapping(target = "isbn", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(BookUpdateDto bookUpdateDto, @MappingTarget Book book);

    Book toEntity(BookUpdateDto bookUpdateDto);
}