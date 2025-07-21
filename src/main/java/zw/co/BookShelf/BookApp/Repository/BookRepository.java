package zw.co.BookShelf.BookApp.Repository;

import zw.co.BookShelf.BookApp.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long>{

    List<Book>  findByTitleContainingIgnoreCase(String title);
    List<Book>  findByAuthorContainingIgnoreCase(String author);
    List<Book> findByGenre(String genre);

    List<Book> findByIsPublished(boolean isPublished);

    List<Book> findTop10ByOrderByCreatedDateDesc(); //latest books

    List<Book> findByPagesGreaterThan(int pages);

    List<Book> findByPagesBetween(int min, int max);

    Optional<Book> findByTitle(String title); // if titles are unique

}
