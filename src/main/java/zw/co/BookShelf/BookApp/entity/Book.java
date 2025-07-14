package zw.co.BookShelf.BookApp.entity;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a book entity in the BookApp application.
 * This class is used to define the properties and behavior of a book.
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    private String title;

    private String author;

    private String genre;

    @Column(unique = true)
    private String isbn;

    private String description;

    private String coverImageUrl;

    private int publicationYear;

    private int numberOfPages;

    private String language;

    private String summary;
}