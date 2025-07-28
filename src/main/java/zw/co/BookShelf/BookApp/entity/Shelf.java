package zw.co.BookShelf.BookApp.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "shelves")
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private boolean isPublic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "shelf", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBook> userBooks;

    // Constructors
    public Shelf() {
    }

    public Shelf(String name, String description, boolean isPublic, User user) {
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
        this.user = user;
    }

    public Shelf(Long id, String name, String description, boolean isPublic, User user, List<UserBook> userBooks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
        this.user = user;
        this.userBooks = userBooks;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserBook> getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(List<UserBook> userBooks) {
        this.userBooks = userBooks;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isPublic=" + isPublic +
                ", user=" + (user != null ? user.getId() : null) +
                ", userBooks=" + (userBooks != null ? userBooks.size() : 0) +
                '}';
    }
}