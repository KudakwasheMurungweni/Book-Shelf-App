package zw.co.BookShelf.BookApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.BookShelf.BookApp.entity.Shelf;
import java.util.List;

public interface ShelfRepository extends JpaRepository<Shelf, Long> {
    List<Shelf> findByUserId(Long userId);
}