package zw.co.BookShelf.BookApp.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import zw.co.BookShelf.BookApp.Service.BookService;
import zw.co.BookShelf.BookApp.dto.BookDto.BookSummaryDto;
import zw.co.BookShelf.BookApp.dto.BookDto.BookResponseDto;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void testGetAllBooks() throws Exception {
        Mockito.when(bookService.getAllBooks()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetBookById_found() throws Exception {
        BookResponseDto dto = new BookResponseDto();
        Mockito.when(bookService.getBookById(1L)).thenReturn(Optional.of(dto));
        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBookById_notFound() throws Exception {
        Mockito.when(bookService.getBookById(2L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/books/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetBookByTitle_notFound() throws Exception {
        Mockito.when(bookService.getBookByTitle("missing")).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/books/title/missing"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateBook_invalidPayload() throws Exception {
        // Missing required fields
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateBook_notFound() throws Exception {
        Mockito.when(bookService.updateBook(any())).thenThrow(new RuntimeException("Not found"));
        mockMvc.perform(put("/api/books/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteBook_notFound() throws Exception {
        Mockito.doThrow(new RuntimeException("Not found")).when(bookService).deleteBookById(99L);
        mockMvc.perform(delete("/api/books/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetBooksPaged() throws Exception {
        Page<BookSummaryDto> page = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);
        Mockito.when(bookService.getAllBooksPaged(any(), any())).thenReturn(page);
        mockMvc.perform(get("/api/books/paged?page=0&size=10&sort=title,asc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetBooksPaged_sortingAndPagination() throws Exception {
        Page<BookSummaryDto> page = new PageImpl<>(Collections.emptyList(), PageRequest.of(1, 5, org.springframework.data.domain.Sort.by("title")), 0);
        Mockito.when(bookService.getAllBooksPaged(any(), any())).thenReturn(page);
        mockMvc.perform(get("/api/books/paged?page=1&size=5&sort=title,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
