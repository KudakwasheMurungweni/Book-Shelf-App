package zw.co.BookShelf.BookApp.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookCreateDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookResponseDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookSummaryDto;
import zw.co.BookShelf.BookApp.dto.UserBookDto.UserBookUpdateDto;
import zw.co.BookShelf.BookApp.entity.UserBook;

@Mapper(componentModel = "spring", uses = {UserMapper.class, BookMapper.class})
public interface UserBookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "book.bookId", source = "bookId")
    @Mapping(target = "addedDate", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "completedDate", ignore = true)
    UserBook toEntity(UserBookCreateDto userBookCreateDto);

    UserBookResponseDto toResponseDto(UserBook userBook);

    @Mapping(target = "bookId", source = "book.bookId")
    @Mapping(target = "bookTitle", source = "book.title")
    @Mapping(target = "bookAuthor", source = "book.author")
    @Mapping(target = "bookCoverImageUrl", source = "book.coverImageUrl")
    UserBookSummaryDto toSummaryDto(UserBook userBook);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "book", ignore = true)
    @Mapping(target = "addedDate", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    void updateEntityFromDto(UserBookUpdateDto userBookUpdateDto, @MappingTarget UserBook userBook);

    UserBook toEntity(UserBookUpdateDto userBookUpdateDto);
}