package zw.co.BookShelf.BookApp.dto.BookDto;

public class BookSummaryDto {

    private Long bookId;
    private String title;
    private String author;
    private String genre;
    private String coverImageUrl;
    private Integer publicationYear;
    private Integer numberOfPages;

    // Constructors
    public BookSummaryDto() {}

    public BookSummaryDto(Long bookId, String title, String author, String genre,
                          String coverImageUrl, Integer publicationYear, Integer numberOfPages) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.coverImageUrl = coverImageUrl;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
    }

    // Getters and Setters
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

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
}