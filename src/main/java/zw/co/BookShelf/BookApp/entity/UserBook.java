package zw.co.BookShelf.BookApp.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Represents a user-book relationship entity in the BookApp application.
 * This class is used to define the properties and behavior of a user-book relationship.
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private String status;// e.g., "reading", "completed", "want to read"

    private int currentPage; // Current page the user is on in the book

    private String progress; // Progress in the book (e.g., percentage read)

    private String isFavorite; // Indicates if the book is marked as a favorite by the user

    private boolean isPublicShelf; // Indicates if the user's bookshelf is public or private

    @Column(length = 500)
    private String notes; // User's personal notes about the book

    private int rating; // User's rating for the book (e.g., 1-5 stars)

    @Column(name = "added_date", nullable = false)
    private LocalDateTime addedDate; // When the book was added to user's shelf

    @Column(name = "started_date")
    private LocalDateTime startedDate; // When user started reading

    @Column(name = "completed_date")
    private LocalDateTime completedDate; // When user completed the book

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated; // Last time the record was updated

    @PrePersist
    protected void onCreate() {
        addedDate = LocalDateTime.now();
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }
}