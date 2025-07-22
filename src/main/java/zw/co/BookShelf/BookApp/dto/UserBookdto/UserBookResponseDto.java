package zw.co.BookShelf.BookApp.dto.UserBookDto;

import zw.co.BookShelf.BookApp.dto.UserDto.UserResponseDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookResponseDto;
import java.time.LocalDateTime;

public class UserBookResponseDto {

    private Long id;
    private UserResponseDto user;
    private BookResponseDto book;
    private String status;
    private Integer currentPage;
    private String progress;
    private String isFavorite;
    private Boolean isPublicShelf;
    private String notes;
    private Integer rating;
    private LocalDateTime addedDate;
    private LocalDateTime startedDate;
    private LocalDateTime completedDate;
    private LocalDateTime lastUpdated;

    // Constructors
    public UserBookResponseDto() {}

    public UserBookResponseDto(Long id, UserResponseDto user, BookResponseDto book, String status,
                               Integer currentPage, String progress, String isFavorite,
                               Boolean isPublicShelf, String notes, Integer rating,
                               LocalDateTime addedDate, LocalDateTime startedDate,
                               LocalDateTime completedDate, LocalDateTime lastUpdated) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.status = status;
        this.currentPage = currentPage;
        this.progress = progress;
        this.isFavorite = isFavorite;
        this.isPublicShelf = isPublicShelf;
        this.notes = notes;
        this.rating = rating;
        this.addedDate = addedDate;
        this.startedDate = startedDate;
        this.completedDate = completedDate;
        this.lastUpdated = lastUpdated;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }

    public BookResponseDto getBook() {
        return book;
    }

    public void setBook(BookResponseDto book) {
        this.book = book;
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

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
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

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}