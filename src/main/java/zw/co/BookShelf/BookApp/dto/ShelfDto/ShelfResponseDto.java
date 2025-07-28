package zw.co.BookShelf.BookApp.dto.ShelfDto;

public class ShelfResponseDto {
    private Long id;
    private String name;
    private String description;
    private boolean isPublic;
    private Long userId;

    public ShelfResponseDto() {
    }

    public ShelfResponseDto(Long id, String name, String description, boolean isPublic, Long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
        this.userId = userId;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ShelfResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isPublic=" + isPublic +
                ", userId=" + userId +
                '}';
    }
}