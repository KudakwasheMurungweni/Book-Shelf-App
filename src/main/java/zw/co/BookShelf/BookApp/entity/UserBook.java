package zw.co.BookShelf.BookApp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_books", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "book_id"})
})
@EntityListeners(AuditingEntityListener.class)
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReadingStatus.Status status = ReadingStatus.Status.WANT_TO_READ;

    @Column(nullable = false)
    private Integer currentPage = 0;

    @Column(nullable = false)
    private Integer progressPercentage = 0;

    @Column(nullable = false)
    private Boolean isFavorite = false;

    @Column(nullable = false)
    private Boolean isPublicShelf = false;

    @Column(length = 500)
    private String notes;

    private Integer rating; // 1-5 stars

    private LocalDateTime startedDate;

    private LocalDateTime finishedDate;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedDate;
}