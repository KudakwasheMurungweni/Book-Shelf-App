package zw.co.BookShelf.BookApp.dto.UserBookDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class UserBookCreateDto {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Book ID is required")
    private Long bookId;

    @Size(max = 50, message = "Status must not exceed 50 characters")
    private String status = "want to read"; // Default status

    @Min(value = 0, message = "Current page cannot be negative")
    private Integer currentPage = 0;

    @Size(max = 10, message = "Progress must not exceed 10 characters")
    private String progress = "0%";

    @Size(max = 10, message = "IsFavorite must not exceed 10 characters")
    private String isFavorite = "no";

    private Boolean isPublicShelf = false;

    @Size(max = 500, message = "Notes must not exceed 500 characters")
    private String notes;

    @Min(value = 0, message = "Rating cannot be negative")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private Integer rating = 0;

    private LocalDateTime startedDate;

    // Constructors
    public UserBookCreateDto() {}

    public UserBookCreateDto(Long userId, Long bookId, String status, Integer currentPage,
                             String progress, String isFavorite, Boolean isPublicShelf,
                             String notes, Integer rating, LocalDateTime startedDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.status = status;
        this.currentPage = currentPage;
        this.progress = progress;
        this.isFavorite = isFavorite;
        this.isPublicShelf = isPublicShelf;
        this.notes = notes;
        this.rating = rating;
        this.startedDate = startedDate;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(String isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Boolean getIsPublicShelf() {
        return isPublicShelf;
    }

    public void setIsPublicShelf(Boolean isPublicShelf) {
        this.isPublicShelf = isPublicShelf;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDateTime getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(LocalDateTime startedDate) {
        this.startedDate = startedDate;
    }
}