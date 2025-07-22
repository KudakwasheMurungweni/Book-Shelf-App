package zw.co.BookShelf.BookApp.dto.UserBookDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class UserBookUpdateDto {

    private Long id;

    @Size(max = 50, message = "Status must not exceed 50 characters")
    private String status;

    @Min(value = 0, message = "Current page cannot be negative")
    private Integer currentPage;

    @Size(max = 10, message = "Progress must not exceed 10 characters")
    private String progress;

    @Size(max = 10, message = "IsFavorite must not exceed 10 characters")
    private String isFavorite;

    private Boolean isPublicShelf;

    @Size(max = 500, message = "Notes must not exceed 500 characters")
    private String notes;

    @Min(value = 0, message = "Rating cannot be negative")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private Integer rating;

    private LocalDateTime startedDate;

    private LocalDateTime completedDate;

    // Constructors
    public UserBookUpdateDto() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDateTime completedDate) {
        this.completedDate = completedDate;
    }
}