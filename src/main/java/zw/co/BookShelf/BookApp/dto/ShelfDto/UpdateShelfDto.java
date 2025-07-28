package zw.co.BookShelf.BookApp.dto.ShelfDto;

public class UpdateShelfDto {
    private String name;
    private String description;
    private boolean isPublic;

    public UpdateShelfDto() {
    }

    public UpdateShelfDto(String name, String description, boolean isPublic) {
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
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

    @Override
    public String toString() {
        return "UpdateShelfDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isPublic=" + isPublic +
                '}';
    }
}