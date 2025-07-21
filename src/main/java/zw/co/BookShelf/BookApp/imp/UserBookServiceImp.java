package zw.co.BookShelf.BookApp.imp;

import zw.co.BookShelf.BookApp.entity.UserBook;
import zw.co.BookShelf.BookApp.Service.UserBookService;
import zw.co.BookShelf.BookApp.Repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserBookServiceImp implements UserBookService {

    private final UserBookRepository userBookRepository;

    @Autowired
    public UserBookServiceImp(UserBookRepository userBookRepository) {
        this.userBookRepository = userBookRepository;
    }

    @Override
    public List<UserBook> getAllUserBooks() {
        return userBookRepository.findAll();
    }

    @Override
    public Optional<UserBook> getUserBookById(Long id) {
        return userBookRepository.findById(id);
    }

    @Override
    public List<UserBook> getUserBooksByUserId(Long userId) {
        return userBookRepository.findByUserId(userId);
    }

    @Override
    public List<UserBook> getUserBooksByBookId(Long bookId) {
        return userBookRepository.findByBookId(bookId);
    }

    @Override
    public Optional<UserBook> getUserBookByUserIdAndBookId(Long userId, Long bookId) {
        return userBookRepository.findByUserIdAndBookId(userId, bookId);
    }

    @Override
    public List<UserBook> getUserBooksByUserIdAndStatus(Long userId, String status) {
        return userBookRepository.findByUserIdAndStatus(userId, status);
    }

    @Override
    public List<UserBook> getUserBooksOrderByDateDesc(Long userId) {
        return userBookRepository.findByUserIdOrderByAddedDateDesc(userId);
    }

    @Override
    public List<UserBook> getRecentlyAddedUserBooks(Long userId) {
        return userBookRepository.findTop5ByUserIdOrderByAddedDateDesc(userId);
    }

    @Override
    public boolean existsByUserIdAndBookId(Long userId, Long bookId) {
        return userBookRepository.existsByUserIdAndBookId(userId, bookId);
    }

    @Override
    public int countUserBooksByStatus(Long userId, String status) {
        return userBookRepository.countByUserIdAndStatus(userId, status);
    }

    @Override
    public UserBook saveUserBook(UserBook userBook) {
        return userBookRepository.save(userBook);
    }

    @Override
    public void deleteUserBook(UserBook userBook) {
        userBookRepository.delete(userBook);
    }

    @Override
    public void deleteUserBookById(Long id) {
        userBookRepository.deleteById(id);
    }
}