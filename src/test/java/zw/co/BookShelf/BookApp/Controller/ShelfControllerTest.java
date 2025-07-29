package zw.co.BookShelf.BookApp.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import zw.co.BookShelf.BookApp.Service.ShelfService;
import zw.co.BookShelf.BookApp.dto.ShelfDto.CreateShelfDto;
import zw.co.BookShelf.BookApp.dto.ShelfDto.ShelfResponseDto;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShelfController.class)
class ShelfControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShelfService shelfService;

    @Test
    void testGetMyShelves() throws Exception {
        Mockito.when(shelfService.getUserShelves(any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/shelves"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetPublicShelves() throws Exception {
        Mockito.when(shelfService.getAllPublicShelves()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/shelves/public"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateShelf() throws Exception {
        ShelfResponseDto responseDto = new ShelfResponseDto();
        Mockito.when(shelfService.createShelf(any(CreateShelfDto.class), any())).thenReturn(responseDto);
        mockMvc.perform(post("/api/shelves")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateShelf_invalidPayload() throws Exception {
        // Missing required fields
        mockMvc.perform(post("/api/shelves")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"invalidField\":\"value\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateShelf_notFound() throws Exception {
        Mockito.when(shelfService.updateShelf(any(Long.class), any(), any(Long.class)))
                .thenThrow(new RuntimeException("Not found"));
        mockMvc.perform(put("/api/shelves/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated\"}"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testDeleteShelf_notFound() throws Exception {
        Mockito.doThrow(new RuntimeException("Not found")).when(shelfService).deleteShelf(eq(99L), any(Long.class));
        mockMvc.perform(delete("/api/shelves/99"))
                .andExpect(status().isInternalServerError());
    }
}
