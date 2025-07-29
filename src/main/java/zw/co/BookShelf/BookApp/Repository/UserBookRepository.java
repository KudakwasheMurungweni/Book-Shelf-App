package zw.co.BookShelf.BookApp.Repository;

import zw.co.BookShelf.BookApp.entity.UserBook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

import java.time.LocalDateTime;

public interface UserBookRepository extends JpaRepository<UserBook, Long>{
    List<UserBook> findByUserId(Long userId);

    List<UserBook> findByBookId(Long bookId);

    Optional<UserBook> findByUserIdAndBookId(Long userId, Long bookId);

    List<UserBook> findByUserIdAndStatus(Long userId, ReadingStatus.Status status);

    List<UserBook> findByUserIdOrderByAddedDateDesc(Long userId);

    List<UserBook> findTop5ByUserIdOrderByAddedDateDesc(Long userId) ; //recently added

    List<UserBook> findByShelfId(Long shelfId);

    boolean existsByUserIdAndBookIdAndShelfId(Long userId, Long bookId, Long shelfId);
}
