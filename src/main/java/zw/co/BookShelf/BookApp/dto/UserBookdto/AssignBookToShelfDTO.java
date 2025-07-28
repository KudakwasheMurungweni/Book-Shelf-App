package zw.co.BookShelf.BookApp.dto.UserBookDto;

public class AssignBookToShelfDTO {
    private Long bookId;
    private Long shelfId;

    public AssignBookToShelfDTO() {}

    public AssignBookToShelfDTO(Long bookId, Long shelfId) {
        this.bookId = bookId;
        this.shelfId = shelfId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }
}

