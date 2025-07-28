package zw.co.BookShelf.BookApp.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zw.co.BookShelf.BookApp.Repository.ShelfRepository;
import zw.co.BookShelf.BookApp.Repository.UserRepository;
import zw.co.BookShelf.BookApp.mapper.ShelfMapper;
import zw.co.BookShelf.BookApp.Service.ShelfService;
import zw.co.BookShelf.BookApp.dto.ShelfDto.*;
import zw.co.BookShelf.BookApp.entity.Shelf;
import zw.co.BookShelf.BookApp.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShelfServiceImpl implements ShelfService {

    private static final Logger logger = LoggerFactory.getLogger(ShelfServiceImpl.class);

    private final ShelfRepository shelfRepository;
    private final UserRepository userRepository;
    private final ShelfMapper shelfMapper;

    @Autowired
    public ShelfServiceImpl(ShelfRepository shelfRepository, UserRepository userRepository, ShelfMapper shelfMapper) {
        this.shelfRepository = shelfRepository;
        this.userRepository = userRepository;
        this.shelfMapper = shelfMapper;
    }

    @Override
    @Transactional
    public ShelfResponseDto createShelf(CreateShelfDto dto, Long userId) {
        logger.info("Creating shelf for userId={}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found: {}", userId);
                    return new RuntimeException("User not found");
                });
        Shelf shelf = shelfMapper.toEntity(dto);
        shelf.setUser(user);
        Shelf savedShelf = shelfRepository.save(shelf);
        logger.info("Shelf created with id={}", savedShelf.getId());
        return shelfMapper.toResponseDto(savedShelf);
    }

    @Override
    public List<ShelfResponseDto> getUserShelves(Long userId) {
        logger.info("Fetching shelves for userId={}", userId);
        return shelfRepository.findByUserId(userId)
                .stream()
                .map(shelfMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ShelfResponseDto updateShelf(Long shelfId, UpdateShelfDto dto, Long userId) {
        logger.info("Updating shelfId={} for userId={}", shelfId, userId);
        Shelf shelf = shelfRepository.findById(shelfId)
                .orElseThrow(() -> {
                    logger.error("Shelf not found: {}", shelfId);
                    return new RuntimeException("Shelf not found");
                });
        if (!shelf.getUser().getId().equals(userId)) {
            logger.error("User {} attempted to update shelf {} not owned by them", userId, shelfId);
            throw new RuntimeException("Not your shelf");
        }
        shelfMapper.updateEntityFromDto(dto, shelf);
        Shelf updatedShelf = shelfRepository.save(shelf);
        logger.info("Shelf updated with id={}", updatedShelf.getId());
        return shelfMapper.toResponseDto(updatedShelf);
    }

    @Override
    @Transactional
    public void deleteShelf(Long shelfId, Long userId) {
        logger.info("Deleting shelfId={} for userId={}", shelfId, userId);
        Shelf shelf = shelfRepository.findById(shelfId)
                .orElseThrow(() -> {
                    logger.error("Shelf not found: {}", shelfId);
                    return new RuntimeException("Shelf not found");
                });
        if (!shelf.getUser().getId().equals(userId)) {
            logger.error("User {} attempted to delete shelf {} not owned by them", userId, shelfId);
            throw new RuntimeException("Not your shelf");
        }
        shelfRepository.delete(shelf);
        logger.info("Shelf deleted with id={}", shelfId);
    }

    @Override
    public List<ShelfResponseDto> getAllPublicShelves() {
        return shelfRepository.findAll()
                .stream()
                .filter(Shelf::isPublic)
                .map(shelfMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}