package zw.co.BookShelf.BookApp.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import zw.co.BookShelf.BookApp.Service.UserService;
import zw.co.BookShelf.BookApp.dto.UserDto.UserResponseDto;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetAllUsers() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetUserById_found() throws Exception {
        UserResponseDto dto = new UserResponseDto();
        Mockito.when(userService.getUserById(1L)).thenReturn(Optional.of(dto));
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserById_notFound() throws Exception {
        Mockito.when(userService.getUserById(2L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/users/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateUser_invalidPayload() throws Exception {
        // Missing required fields
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateUser_notFound() throws Exception {
        Mockito.when(userService.updateUser(any())).thenThrow(new RuntimeException("Not found"));
        mockMvc.perform(put("/api/users/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"Updated\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteUser_notFound() throws Exception {
        Mockito.doThrow(new RuntimeException("Not found")).when(userService).deleteUser(99L);
        mockMvc.perform(delete("/api/users/99"))
                .andExpect(status().isNotFound());
    }
}
