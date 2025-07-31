package zw.co.BookShelf.BookApp.dto;

import java.util.List;

public class GoogleBookSearchResponseDto {
    private int totalItems;
    private List<GoogleBookPreviewDto> books;

    // Getters and setters
    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<GoogleBookPreviewDto> getBooks() {
        return books;
    }

    public void setBooks(List<GoogleBookPreviewDto> books) {
        this.books = books;
    }
}
