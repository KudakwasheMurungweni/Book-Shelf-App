package zw.co.BookShelf.BookApp.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import zw.co.BookShelf.BookApp.Service.UserBookService;
import zw.co.BookShelf.BookApp.dto.UserBookDto.AssignBookToShelfDTO;
import zw.co.BookShelf.BookApp.entity.Book;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserBookController.class)
class UserBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserBookService userBookService;

    @Test
    void testAssignBookToShelf() throws Exception {
        Mockito.doNothing().when(userBookService).assignBookToShelf(any(Long.class), any(AssignBookToShelfDTO.class));
        mockMvc.perform(post("/api/userbooks/assign")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"bookId\":1,\"shelfId\":2}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBooksByShelf() throws Exception {
        Mockito.when(userBookService.getBooksByShelf(any(Long.class))).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/userbooks/shelf/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testAssignBookToShelf_invalidPayload() throws Exception {
        // Missing required fields
        mockMvc.perform(post("/api/userbooks/assign")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"invalidField\":123}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetBooksByShelf_notFound() throws Exception {
        Mockito.when(userBookService.getBooksByShelf(99L)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/userbooks/shelf/99"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
