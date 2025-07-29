package zw.co.BookShelf.BookApp.Mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import zw.co.BookShelf.BookApp.dto.ShelfDto.CreateShelfDto;
import zw.co.BookShelf.BookApp.dto.ShelfDto.ShelfResponseDto;
import zw.co.BookShelf.BookApp.dto.ShelfDto.UpdateShelfDto;
import zw.co.BookShelf.BookApp.entity.Shelf;

import static org.assertj.core.api.Assertions.assertThat;

class ShelfMapperTest {

    private final ShelfMapper mapper = Mappers.getMapper(ShelfMapper.class);

    @Test
    void testToEntityFromCreateDto() {
        CreateShelfDto dto = new CreateShelfDto();
        dto.setName("My Shelf");
        Shelf shelf = mapper.toEntity(dto);
        assertThat(shelf).isNotNull();
        assertThat(shelf.getName()).isEqualTo("My Shelf");
    }

    @Test
    void testToResponseDto() {
        Shelf shelf = new Shelf();
        shelf.setName("Public Shelf");
        ShelfResponseDto response = mapper.toResponseDto(shelf);
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Public Shelf");
    }

    @Test
    void testUpdateEntityFromDto() {
        Shelf shelf = new Shelf();
        shelf.setName("Old Name");
        UpdateShelfDto dto = new UpdateShelfDto();
        dto.setName("New Name");
        mapper.updateEntityFromDto(dto, shelf);
        assertThat(shelf.getName()).isEqualTo("New Name");
    }
}
