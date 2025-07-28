package zw.co.BookShelf.BookApp.Service;

import zw.co.BookShelf.BookApp.dto.ShelfDto.*;
import java.util.List;

/**
 * Service interface for managing shelves.
 */
public interface ShelfService {
    /**
     * Creates a new shelf for the specified user.
     * @param dto the shelf creation data
     * @param userId the ID of the user
     * @return the created shelf as a response DTO
     */
    ShelfResponseDto createShelf(CreateShelfDto dto, Long userId);

    /**
     * Retrieves all shelves for the specified user.
     * @param userId the ID of the user
     * @return a list of shelf response DTOs
     */
    List<ShelfResponseDto> getUserShelves(Long userId);

    /**
     * Updates an existing shelf for the specified user.
     * @param shelfId the ID of the shelf to update
     * @param dto the update data
     * @param userId the ID of the user
     * @return the updated shelf as a response DTO
     */
    ShelfResponseDto updateShelf(Long shelfId, UpdateShelfDto dto, Long userId);

    /**
     * Deletes a shelf for the specified user.
     * @param shelfId the ID of the shelf to delete
     * @param userId the ID of the user
     */
    void deleteShelf(Long shelfId, Long userId);

    /**
     * Retrieves a single shelf by its ID for the specified user.
     * @param shelfId the ID of the shelf
     * @param userId the ID of the user
     * @return the shelf as a response DTO
     */
    ShelfResponseDto getShelfById(Long shelfId, Long userId);

    /**
     * Retrieves all public shelves.
     * @return list of public shelf response DTOs
     */
    List<ShelfResponseDto> getAllPublicShelves();
}