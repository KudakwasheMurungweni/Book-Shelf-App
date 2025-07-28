package zw.co.BookShelf.BookApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.BookShelf.BookApp.Service.ShelfService;
import zw.co.BookShelf.BookApp.dto.ShelfDto.*;

import java.util.List;

@RestController
@RequestMapping("/api/shelves")
public class ShelfController {

    private final ShelfService shelfService;

    @Autowired
    public ShelfController(ShelfService shelfService) {
        this.shelfService = shelfService;
    }

    // Replace mockUserId with actual user ID from security context in production
    private final Long mockUserId = 1L;

    @PostMapping
    public ResponseEntity<ShelfResponseDto> createShelf(@RequestBody CreateShelfDto dto) {
        return ResponseEntity.ok(shelfService.createShelf(dto, mockUserId));
    }

    @GetMapping
    public ResponseEntity<List<ShelfResponseDto>> getMyShelves() {
        return ResponseEntity.ok(shelfService.getUserShelves(mockUserId));
    }

    @PutMapping("/{shelfId}")
    public ResponseEntity<ShelfResponseDto> updateShelf(@PathVariable Long shelfId, @RequestBody UpdateShelfDto dto) {
        return ResponseEntity.ok(shelfService.updateShelf(shelfId, dto, mockUserId));
    }

    @DeleteMapping("/{shelfId}")
    public ResponseEntity<Void> deleteShelf(@PathVariable Long shelfId) {
        shelfService.deleteShelf(shelfId, mockUserId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/public")
    public ResponseEntity<List<ShelfResponseDto>> getPublicShelves() {
        List<ShelfResponseDto> publicShelves = shelfService.getAllPublicShelves();
        return ResponseEntity.ok(publicShelves);
    }
}