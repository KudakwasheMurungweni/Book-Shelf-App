package zw.co.BookShelf.BookApp.Mapper;

import org.mapstruct.*;
import zw.co.BookShelf.BookApp.entity.Shelf;
import zw.co.BookShelf.BookApp.dto.ShelfDto.*;

/**
 * Mapper for converting between Shelf entities and DTOs.
 */
@Mapper(componentModel = "spring")
public interface ShelfMapper {
    /**
     * Converts a CreateShelfDto to a Shelf entity.
     */
    Shelf toEntity(CreateShelfDto dto);

    /**
     * Converts a Shelf entity to a ShelfResponseDto.
     */
    ShelfResponseDto toResponseDto(Shelf shelf);

    /**
     * Updates an existing Shelf entity from an UpdateShelfDto.
     */
    void updateEntityFromDto(UpdateShelfDto dto, @MappingTarget Shelf shelf);
}