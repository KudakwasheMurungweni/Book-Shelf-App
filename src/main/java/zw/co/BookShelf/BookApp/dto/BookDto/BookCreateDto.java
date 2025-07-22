package zw.co.BookShelf.BookApp.dto.BookDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class BookCreateDto {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 255, message = "Author must not exceed 255 characters")
    private String author;

    @Size(max = 100, message = "Genre must not exceed 100 characters")
    private String genre;

    @NotBlank(message = "ISBN is required")
    @Size(min = 10, max = 17, message = "ISBN must be between 10 and 17 characters")
    private String isbn;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    private String coverImageUrl;

    @NotNull(message = "Publication year is required")
    @Min(value = 1000, message = "Publication year must be valid")
    @Max(value = 2030, message = "Publication year cannot be in the future")
    private Integer publicationYear;

    @NotNull(message = "Number of pages is required")
    @Min(value = 1, message = "Number of pages must be at least 1")
    private Integer numberOfPages;

    @Size(max = 50, message = "Language must not exceed 50 characters")
    private String language;

    @Size(max = 2000, message = "Summary must not exceed 2000 characters")
    private String summary;

    // Constructors
    public BookCreateDto() {}

    public BookCreateDto(String title, String author, String genre, String isbn, String description,
                         String coverImageUrl, Integer publicationYear, Integer numberOfPages,
                         String language, String summary) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.description = description;
        this.coverImageUrl = coverImageUrl;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
        this.language = language;
        this.summary = summary;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}